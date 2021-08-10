package com.peg.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtils implements ApplicationContextAware {

	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext cont)
			throws BeansException {
		context = cont;
	}

	public ApplicationContext getApplicationContext(){
		return context;
	}
	
	public static Object getBean(String beanName){
		if(context != null)
			return context.getBean(beanName);
		return null;
	}
}
