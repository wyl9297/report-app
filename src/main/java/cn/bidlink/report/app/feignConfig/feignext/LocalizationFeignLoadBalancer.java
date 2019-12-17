package cn.bidlink.report.app.feignConfig.feignext;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.feign.ribbon.FeignLoadBalancer;
import org.springframework.cloud.netflix.ribbon.ServerIntrospector;

import java.net.URI;

/**
 * 拦截reconstructURIWithServer 方法 根据需求改写参数Server中的ip地址 并交给父类继续执行
 *
 * @author 王炎林 :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2019-11-19 10:52:21
 */
public class LocalizationFeignLoadBalancer extends FeignLoadBalancer {

    private String ipAddr;

    public LocalizationFeignLoadBalancer(ILoadBalancer lb, IClientConfig clientConfig, ServerIntrospector serverIntrospector , String ipAddr) {
        super(lb, clientConfig, serverIntrospector);
        this.ipAddr = ipAddr;
    }

    public LocalizationFeignLoadBalancer(ILoadBalancer lb, IClientConfig clientConfig, ServerIntrospector serverIntrospector) {
        super(lb, clientConfig, serverIntrospector);
    }

    @Override
    public URI reconstructURIWithServer(Server server, URI original) {
        if (StringUtils.isNotEmpty(ipAddr)) {
            server = new Server(ipAddr);
        }
        return super.reconstructURIWithServer(server, original);
    }

}