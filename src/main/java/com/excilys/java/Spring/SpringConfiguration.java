package com.excilys.java.Spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


@Configuration
@ComponentScan("com.excilys.java")
public class SpringConfiguration extends AbstractContextLoaderInitializer {
	
	@Override
	  protected WebApplicationContext createRootApplicationContext() {
	    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
	    applicationContext.register(SpringConfiguration.class);
	    return applicationContext;
	  }

}
