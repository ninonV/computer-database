package com.excilys.java.CDB.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userService;

	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/RegisterUser").permitAll()
            .antMatchers(HttpMethod.GET, "/", "/ListComputer/**").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.POST, "/", "/ListComputer/**").hasRole("ADMIN")
            .antMatchers("/AddComputer/**").hasRole("ADMIN")
            .antMatchers("/EditComputer/**").hasRole("ADMIN")
            .and().formLogin().loginPage("/login").defaultSuccessUrl("/ListComputer").failureUrl("/login?error=true").permitAll()
            .and().logout()
            .and().csrf().disable();
    		//.addFilter(digestAuthenticationFilter());
    }

	/*DigestAuthenticationFilter digestAuthenticationFilter() throws Exception {
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
	}*/
	
	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		//authManagerBuilder.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
		authManagerBuilder.userDetailsService(userService);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}
