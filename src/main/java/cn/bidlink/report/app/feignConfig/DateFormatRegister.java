package cn.bidlink.report.app.feignConfig;

import org.springframework.cloud.netflix.feign.FeignFormatterRegistrar;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 客户端
 * Feign 传递 Date 时间类型参数
 * 解决SpringCloud Feign 传输Date类型参数的时差问题
 * 规定Feign在将Date参数转化成String参数的格式
 */
@Component
public class DateFormatRegister implements FeignFormatterRegistrar {

    public DateFormatRegister() {
    }

    @Override
    public void registerFormatters(FormatterRegistry registry) {
        registry.addConverter(Date.class, String.class, new Date2StringConverter());
    }

    /**
     * 自定义 Converter 转化器
     */
    private class Date2StringConverter implements Converter<Date, String> {
        @Override
        public String convert(Date source) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(source);
        }
    }
}
