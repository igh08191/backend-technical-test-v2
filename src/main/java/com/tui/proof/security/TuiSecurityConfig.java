package com.tui.proof.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TuiSecurityConfig extends WebSecurityConfigurerAdapter {
	
	public enum UserRol {
        ADMIN, USER;
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	    auth.inMemoryAuthentication()
	            .withUser("ADMIN").password(encoder.encode("admin"))
	               .roles(UserRol.ADMIN.name()).and()
	            .withUser("user").password(encoder.encode("user"))
	               .roles(UserRol.USER.name());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	    .and().csrf().disable()
	    .authorizeRequests()//.antMatchers(HttpMethod.GET, "/search/*").authenticated()
	                        //.antMatchers(HttpMethod.DELETE, "/search/*").hasRole(UserRol.ADMIN.name())
	                        .antMatchers("/**").permitAll()
	    .and().httpBasic();
	}
}
