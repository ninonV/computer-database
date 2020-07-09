package com.excilys.java.Spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@ComponentScan({"com.excilys.java.persistence" , "com.excilys.java.service" , "com.excilys.java.ui"})
public class SpringConfiguration extends AbstractContextLoaderInitializer {
	
	@Override
	  protected WebApplicationContext createRootApplicationContext() {
	    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
	    applicationContext.register(SpringConfiguration.class);
	    return applicationContext;
	  }
	
	@Bean
	public HikariDataSource dataSource() {
		return new HikariDataSource(new HikariConfig("/datasource.properties"));
	}
}
