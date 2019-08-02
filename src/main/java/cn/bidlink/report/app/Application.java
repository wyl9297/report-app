package cn.bidlink.report.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/**
 * 扫描 路径 scanBasePackages cn.bidlink.report.app 为当前业务基包 默认可不写
 * @author  :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2019-04-02 11:28:27
 */
@SpringBootApplication(scanBasePackages = {"cn.bidlink.report.app"})
@ImportResource(locations = {"classpath:application-dubbo.xml"})
public class Application extends SpringBootServletInitializer{

    public static void main(String[] args) {
        //运行WEB
        SpringApplication.run(Application.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

}
