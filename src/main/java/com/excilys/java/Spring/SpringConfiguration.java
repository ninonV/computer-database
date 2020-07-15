package com.excilys.java.Spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@ComponentScan({"com.excilys.java.persistence" , "com.excilys.java.service", "com.excilys.java.controller" , "com.excilys.java.ui"})
public class SpringConfiguration extends AbstractContextLoaderInitializer {
	
	@Override
	  protected WebApplicationContext createRootApplicationContext() {
	    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
	    applicationContext.register(SpringConfiguration.class, SpringMVCConfiguration.class);
	    return applicationContext;
	  }
	
	@Bean
	public HikariDataSource dataSource() {
		HikariConfig config = new HikariConfig("/datasource.properties");
		return new HikariDataSource(config);
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
}
