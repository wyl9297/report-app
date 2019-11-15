package cn.bidlink.report.app.feignConfig.feignext;

import feign.Client;
import org.springframework.cloud.netflix.feign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 王炎林
 */
@Configuration
public class LocalizationFeignConfig {

    @Bean
    public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory,
                              SpringClientFactory clientFactory) {

        return new LocalizationLoadBalancerFeignClient(new Client.Default(null, null),
                cachingFactory, clientFactory);
    }


   /* @Bean
    public CachingSpringLoadBalancerFactory cachingLocalLBClientFactory(
            SpringClientFactory factory) {
        return new LocalizationCachingSpringLoadBalancerFactory(factory);
    }*/

}
