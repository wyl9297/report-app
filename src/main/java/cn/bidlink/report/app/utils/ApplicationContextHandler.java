package cn.bidlink.report.app.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class ApplicationContextHandler implements ApplicationContextAware {
	private static ApplicationContext ac;
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			 {
		ac = arg0;
	}
	public static ApplicationContext getHandler(){
		return ac;
	}
	
}