package cn.bidlink.report.app.feignConfig.feignext;

import cn.bidlink.framework.boot.web.context.UserContext;
import feign.Client;
import feign.Request;
import feign.Response;
import org.springframework.cloud.netflix.feign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.netflix.feign.ribbon.LoadBalancerFeignClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 这里拦截了 LoadBalancerFeignClient 的 execute方法 可以改写第一个参数Request中的url并交给父类执行
 *
 * @author 王炎林 :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2019-11-19 10:51:48
 */
public class LocalizationLoadBalancerFeignClient extends LoadBalancerFeignClient {

    static final Request.Options DEFAULT_OPTIONS = new Request.Options();

    private final Client delegate;
    private CachingSpringLoadBalancerFactory lbClientFactory;
    private SpringClientFactory clientFactory;

    public LocalizationLoadBalancerFeignClient(Client delegate, CachingSpringLoadBalancerFactory lbClientFactory, SpringClientFactory clientFactory) {
        super(delegate, lbClientFactory, clientFactory);
        this.delegate = delegate;
        this.lbClientFactory = lbClientFactory;
        this.clientFactory = clientFactory;
    }

    @Override
    public Response execute(Request requestOri, Request.Options options) throws IOException {
        return super.execute(requestOri,options);
    }

}
