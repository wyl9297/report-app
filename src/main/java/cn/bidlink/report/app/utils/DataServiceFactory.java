package cn.bidlink.report.app.utils;

import org.springframework.context.ApplicationContext;

public class DataServiceFactory {

    ApplicationContext applicationContext = ApplicationContextHandler.getHandler();

    public <V> V getDataService(Class requireType , String name){
        return (V)applicationContext.getBean(name, requireType);
    }

    public <V> V getDataService(Class requireType){
        return (V) applicationContext.getBean(requireType);
    }

    public <V> V getDataService(String name){
        return (V) applicationContext.getBean(name);
    }

}