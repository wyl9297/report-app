package cn.bidlink.report.app.gateway;


import cn.bidlink.report.app.annotation.Individuation;
import cn.bidlink.report.app.individuation.ReportUrlAppender;
import cn.bidlink.report.app.utils.OrderedProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 报表访问url解析器
 *
 * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2019-06-05 13:43:15
 */
@Component
public class UrlHandler implements ApplicationListener {

    private static final Map<String,String> urlMap = new HashMap();

    private static final Map<String,Map> multiUrlMap = new LinkedHashMap<>();

    public static final Map<String,String> detailMap = new LinkedHashMap<>();

    public static final Map<String,String> templateIdMap = new HashMap<>();

    public static final Map<String,ReportUrlAppender> urlAppenderMap = new HashMap<>();

    public static final StringBuilder multiDetailHtml = new StringBuilder();

    private final String prefix = "report.gateway.route.";

    private final String multiPrefix = "report.gateway.multi.";

    private final String frRealPath = "../../../ReportServer?viewlet=";

    private Logger logger = LoggerFactory.getLogger(UrlHandler.class);

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ContextRefreshedEvent) {
            if( urlMap.size() == 0 ){
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("route.properties");
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
                        String key = keyBuilder.append(split[3]).append("/").append(split[4]).toString();
                        String path = properties.getProperty(name).replace("/","%2F");
                        String[] preValue = pathBuilder.append(frRealPath).append(path).toString().split("#");
                        urlMap.put(key,preValue[0]);
                        logger.info("报表路径：{}->{} 添加完毕！",key,preValue);
                        if( preValue.length > 1 ){
                            detailMap.put(key,preValue[1]);
                        }
                    } else if ( name.startsWith(multiPrefix) ){
                        String value = properties.getProperty(name);
                        Pattern pattern = Pattern.compile("(?<=\\$\\{)(.+?)(?=\\})");
                        Matcher matcher = pattern.matcher(value);
                        Map<String,String> map = new LinkedHashMap();
                        String[] n = name.split("\\.");
                        String multiKey = pathBuilder.append(n[3]).append("/").append(n[4]).toString();
                        while(matcher.find()){
                            keyBuilder.delete(0,keyBuilder.length());
                            if ( StringUtils.isNotEmpty(matcher.group())){
                                String val = matcher.group();
                                if ( val.contains("->") ) {
                                    String[] split = val.split("->");
                                    if ( split.length > 1 ) {
                                        String[] v = split[1].split("\\.");
                                        map.put(keyBuilder.append("@").append(v[0]).append("/").append(v[1]).toString(),split[0]);
                                    }
                                } else {
                                    String[] split = matcher.group().split("#");
                                    if ( split.length > 1 ) {
                                        String[] v = split[1].split("\\.");
                                        map.put(keyBuilder.append(v[0]).append("/").append(v[1]).toString(),split[0]);
                                        if ( split.length > 2 ){
                                            templateIdMap.put(multiKey + "/" + keyBuilder.toString(),split[2]);
                                        }
                                    }
                                }
                            }
                        }
                        if( map.size() > 0 ){
                            multiUrlMap.put(multiKey,map);
                            logger.info("多报表页面路径：{} 添加完毕！" , pathBuilder.toString());
                        }
                        map = null;
                    }
                    if ( keyBuilder.length() > 0 || pathBuilder.length() > 0 ){
                        keyBuilder.delete(0,keyBuilder.length());
                        pathBuilder.delete(0,pathBuilder.length());
                    }
                });
                appendMultiTableHtml();

                individuationUrl();

            }
        }
    }

    private void individuationUrl(){
        Map<String, ReportUrlAppender> beans = applicationContext.getBeansOfType(ReportUrlAppender.class);
        Iterator<String> iterator = beans.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            ReportUrlAppender reportUrlAppender = beans.get(key);
            Individuation annotation = reportUrlAppender.getClass().getAnnotation(Individuation.class);
            if ( null == annotation ) {
                throw new RuntimeException("报表系统启动失败:" + reportUrlAppender.getClass().getName() + "类未声明【Individuation】注解 ");
            }
            String value = annotation.value();
            value = value.replace(".", "/");
            if ( urlAppenderMap.containsKey(value) ) {
                throw new RuntimeException("报表系统启动失败:" + value + "在注解【Individuation】 声明超过一次");
            }
            urlAppenderMap.put(value,reportUrlAppender);
        }
    }

    private void appendMultiTableHtml(){
        int index = 1;
        for (Map.Entry<String, Map> entry : multiUrlMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Map<String,String> valueMap = (Map) value;
            if( valueMap.size() > 0 ){
                int i = 0;
                for (Map.Entry<String, String> e : valueMap.entrySet()) {
                    multiDetailHtml.append("<tr>");
                    if ( i == 0 ){
                        multiDetailHtml.append("<td rowspan='");
                        multiDetailHtml.append(valueMap.size());
                        multiDetailHtml.append("'>");
                        multiDetailHtml.append(index ++);
                        multiDetailHtml.append("</td>");

                        multiDetailHtml.append("<td rowspan='");
                        multiDetailHtml.append(valueMap.size());
                        multiDetailHtml.append("'>");
                        multiDetailHtml.append("/reportGateway/multiWindow/");
                        multiDetailHtml.append(key);
                        multiDetailHtml.append("</td>");
                    }

                    multiDetailHtml.append("<td>");
                    multiDetailHtml.append(e.getValue());
                    multiDetailHtml.append("</td>");

                    multiDetailHtml.append("<td>");
                    multiDetailHtml.append(e.getKey());
                    multiDetailHtml.append("</td>");

                    multiDetailHtml.append("<td>");
                    if ( StringUtils.isNotEmpty(e.getKey()) && e.getKey().startsWith("@") ) {
                        multiDetailHtml.append(e.getKey());
                    } else {
                        multiDetailHtml.append(detailMap.get(e.getKey()));
                    }
                    multiDetailHtml.append("</td>");
                    multiDetailHtml.append("</tr>");
                    i ++;
                }
            }
        }
    }

    public static String getUrl(String key){
        return urlMap.get(key);
    }

    public static Map getMultiUrl(String key){
        return multiUrlMap.get(key);
    }

    public static String getTemplateId(String key){
        return templateIdMap.get(key);
    }
}
