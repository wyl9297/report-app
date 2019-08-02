package cn.bidlink.report.app.gateway;


import cn.bidlink.report.app.utils.OrderedProperties;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 报表访问url解析器
 *
 * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2019-06-05 13:43:15
 */
@Component
public class UrlHandler implements ApplicationListener {

    private static final Map<String,String> urlMap = new HashMap();

    public static final Map<String,String> detailMap = new LinkedHashMap<>();

    private final String prefix = "report.gateway.route.";

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ContextRefreshedEvent) {
            if( urlMap.size() == 0 ){
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
                Properties properties = new OrderedProperties();
                try {
                    properties.load(inputStream);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Set<String> names = properties.stringPropertyNames();
                StringBuilder keyBuilder = new StringBuilder();
                StringBuilder pathBuilder = new StringBuilder();
                names.forEach( name -> {
                    if( name.startsWith(prefix) ){
                        String[] split = name.split("\\.");
                        keyBuilder.delete(0,keyBuilder.length());
                        pathBuilder.delete(0,pathBuilder.length());
                        String key = keyBuilder.append(split[3]).append("/").append(split[4]).toString();
                        String path = properties.getProperty(name).replace("/","%2F");
                        String[] preValue = pathBuilder.append("../../../ReportServer?viewlet=").append(path).toString().split("#");
                        urlMap.put(key,preValue[0]);
                        if( preValue.length > 1 ){
                            detailMap.put(key,preValue[1]);
                        }
                    }
                });
            }
        }
    }

    public static String getUrl(String key){
        return urlMap.get(key);
    }

}
