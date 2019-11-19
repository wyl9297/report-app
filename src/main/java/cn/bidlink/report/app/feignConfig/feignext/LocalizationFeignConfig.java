package cn.bidlink.report.app.feignConfig.feignext;

import feign.Client;
import org.springframework.cloud.netflix.feign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 这里注入了新的feignClient 代替
 * org.springframework.cloud.netflix.feign.ribbon.FeignRibbonClientAutoConfiguration#feignClient
 * 用自定义的 LocalizationCachingSpringLoadBalancerFactory 代替其父类 CachingSpringLoadBalancerFactory
 *
 * @author 王炎林 :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2019-11-19 10:52:28
 */
@Configuration
public class LocalizationFeignConfig {


    @Bean
    public Client feignClient(SpringClientFactory clientFactory) {
        CachingSpringLoadBalancerFactory cachingFactory = new LocalizationCachingSpringLoadBalancerFactory(clientFactory);
        return new LocalizationLoadBalancerFeignClient(new Client.Default(null, null),
                cachingFactory, clientFactory);
    }

    @Bean(name="pureRestTemplate")
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
