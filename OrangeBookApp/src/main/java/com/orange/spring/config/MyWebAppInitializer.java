package com.orange.spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.orange.spring.config.WebConfig;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	   protected Class<?>[] getRootConfigClasses() {
	      return new Class[] { AppConfig.class };
	   }

	@Override
	   protected Class<?>[] getServletConfigClasses() {
	      return new Class[] { WebConfig.class };
	   }

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	 
	
}
