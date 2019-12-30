package cn.bidlink.report.app.feignConfig.feignext;

import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.report.app.utils.ApplicationContextHandler;
import com.alibaba.fastjson.JSONObject;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ILoadBalancer;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.netflix.feign.ribbon.FeignLoadBalancer;
import org.springframework.cloud.netflix.ribbon.ServerIntrospector;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.http.*;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支持本地化的 LoadBalancerFactory
 * create方法 返回了自定义的 LocalizationFeignLoadBalancer类
 * getIpAddr 可以根据本地化的注册中心的地址 获取对应服务的ip 并交给LocalizationFeignLoadBalancer
 *
 * @author 王炎林 :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2019-11-19 10:52:45
 */
public class LocalizationCachingSpringLoadBalancerFactory extends CachingSpringLoadBalancerFactory {

    private final SpringClientFactory factory;

    private volatile Map<String, String> cache = new ConcurrentReferenceHashMap<>();

    private static Map<Long, String> eurekaHostMap = new HashMap<>();

    private Logger logger = LoggerFactory.getLogger(LocalizationCachingSpringLoadBalancerFactory.class);

    static {
        //本地化的注册中心地址
        eurekaHostMap.put(11113174597L , "20.8.0.222:8035");
        eurekaHostMap.put(11113212204L , "20.8.0.222:8035");
        eurekaHostMap.put(11113213708L , "eureka-01.smorganic.cn:8035");
    }


    public LocalizationCachingSpringLoadBalancerFactory(SpringClientFactory factory) {
        super(factory);
        this.factory = factory;
    }

    @Override
    public FeignLoadBalancer create(String clientName) {
        IClientConfig config = this.factory.getClientConfig(clientName);
        ILoadBalancer lb = this.factory.getLoadBalancer(clientName);
        ServerIntrospector serverIntrospector = this.factory.getInstance(clientName, ServerIntrospector.class);
        Long companyId = 0L;
        try {
            companyId = UserContext.getCompanyId();
        } catch (Exception e){
            logger.error("获取公司信息失败");
            return new LocalizationFeignLoadBalancer(lb, config, serverIntrospector);
        }

        //如果 eurekaHostMap中有相应的公司Id 说明该公司是本地化部署 需要去目标注册中心查询服务
        if ( eurekaHostMap.containsKey(companyId) ){
            String ipAddr = getIpAddr(clientName , companyId);
            return new LocalizationFeignLoadBalancer(lb, config, serverIntrospector , ipAddr);
        } else {
            return new LocalizationFeignLoadBalancer(lb, config, serverIntrospector);
        }
    }

    private String getEurekaHost(Long companyId) {
        String host =  eurekaHostMap.get(companyId);
        return "http://" + host;
    }

    private String getIpAddr( String clientName , Long companyId){
        try {
            String cacheKey = clientName + "-" + companyId ;
            if ( cache.containsKey(cacheKey) ) {
                return cache.get(cacheKey);
            }
            RestTemplate restTemplate = (RestTemplate) ApplicationContextHandler.getHandler().getBean("pureRestTemplate");
            //头部信息
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            List<MediaType> acceptList = new ArrayList<>(1);
            acceptList.add(MediaType.APPLICATION_JSON);
            header.setAccept(acceptList);
            //请求body
            MultiValueMap<String, String> value = new LinkedMultiValueMap<>();
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(value, header);
            ResponseEntity<String> resEntity = restTemplate.exchange(getEurekaHost(companyId) + "/eureka/apps/" + clientName, HttpMethod.GET, httpEntity, String.class);
            String body = resEntity.getBody();
            Map<Map,Map<Map,List>> map = (Map) JSONObject.parse(body);
            List<Map<String,String>> list = map.get("application").get("instance");

            //获取服务的ip地址
            String homePageUrl = null;
            for (Map<String, String> stringStringMap : list) {
                String status = stringStringMap.get("status");
                if ( "UP".equals(status) ) {
                    homePageUrl = stringStringMap.get("homePageUrl");
                    if (StringUtils.isNotEmpty(homePageUrl)){
                        cache.put(cacheKey,homePageUrl);
                    }
                    break;
                }
            }
            return homePageUrl;
        } catch (Exception e) {
            return null;
        }
    }

}
