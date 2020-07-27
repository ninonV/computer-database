package com.excilys.java.CDB.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userService;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/RegisterUser").permitAll()
            .antMatchers(HttpMethod.GET, "/", "/ListComputer/**").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.POST, "/", "/ListComputer/**").hasRole("ADMIN")
            .antMatchers("/AddComputer/**").hasRole("ADMIN")
            .antMatchers("/EditComputer/**").hasRole("ADMIN")
            .and().formLogin()
            .and().csrf().disable()
    		.addFilter(digestAuthenticationFilter());
    }

	DigestAuthenticationFilter digestAuthenticationFilter() throws Exception {
		DigestAuthenticationFilter digestAuthenticationFilter = new DigestAuthenticationFilter();
		digestAuthenticationFilter.setUserDetailsService(userService);
		digestAuthenticationFilter.setAuthenticationEntryPoint(digestEntryPoint());
		return digestAuthenticationFilter;
	}

	@Bean
	DigestAuthenticationEntryPoint digestEntryPoint() {
		DigestAuthenticationEntryPoint bauth = new DigestAuthenticationEntryPoint();
		bauth.setRealmName("Digest Authentication");
		bauth.setKey("MySecureKey");
		return bauth;
	}

}
