package com.javaspringboot.api.alkemyjavaspringboot.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.okta.spring.boot.oauth.Okta;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	
	
	
	
	
	
	
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//protect endpoint
		http.authorizeRequests()
			.antMatchers("/genero/**","/characters/**","/movies/**","/user/**")
			.authenticated()
			.and()
			.oauth2ResourceServer()
			.jwt();
		
		
		
		//add CORS filter
		http.cors();
		
		// forces a non-empty response body for 401's 
		Okta.configureResourceServer401ResponseBody(http);
		
	}
	

	
	

}
