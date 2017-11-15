package com.noah.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.addFilter(preAuthenticatedProcessingFilter())
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			    .antMatchers("/students/**").permitAll()
			    .antMatchers("/cookie/**").permitAll()
				.antMatchers("/public").permitAll()
				.antMatchers("/readonly").hasRole(Role.READ_ONLY)
				.antMatchers("/admin").hasRole(Role.ADMIN)
				.antMatchers("/readwrite").hasRole(Role.READ_WRITE)
			    //.anyRequest().denyAll()
		     .and()
		        .exceptionHandling()
		        .authenticationEntryPoint(ExceptionAuthenticationEntryPoint());
		     
		
		
	}
	
	@Bean
	public ExceptionAuthenticationEntryPoint ExceptionAuthenticationEntryPoint(){
		return new ExceptionAuthenticationEntryPoint();
	}
	
	//Pre-Authentication Provider, make use of the cookie from the Pre-Authentication Filter
	//The real Authentication job is processed here.
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(preAuthenticatedAuthenticationProvider());
	}
	
	private AuthenticationProvider preAuthenticatedAuthenticationProvider(){
		PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
		provider.setPreAuthenticatedUserDetailsService(preAuthenticatedUserDetailsService());
		return provider;
	}
	
	@Bean
	public DemoAuthenticatedUserDetailsService preAuthenticatedUserDetailsService(){
		return new DemoAuthenticatedUserDetailsService();
	}
	
	//Pre-Authentication Filter, to get the cookie from the http request. This cookie is a token, stores the user info.
	@Bean
	public PreAuthenticatedProcessingFilter preAuthenticatedProcessingFilter() throws Exception{
		return new PreAuthenticatedProcessingFilter(authenticationManager());
	}
	
	

	
	
	
	

}
