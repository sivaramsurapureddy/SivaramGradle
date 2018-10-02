package com.synergy.recupro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.synergy.config.JwtAuthenticationFilter;

@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	private static final String[] AUTH_WHITELIST = {

			// -- swagger ui
			"/swagger-resources/configuration/security",
			"/swagger-resources/configuration/ui", "/swagger-resources",
			"/swagger-ui.html", "/v2/api-docs", "/webjars/**"

	};

	@Override
	public void configure(
			AuthenticationManagerBuilder authenticationManagerBuilder)
			throws Exception {
		authenticationManagerBuilder.userDetailsService(
				customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.cors()
//				.and()
//				.csrf()
//				.disable()
//				.exceptionHandling()
//				.authenticationEntryPoint(unauthorizedHandler)
//				.and()
//				.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and()
//				.authorizeRequests()
//				.antMatchers("/", "/favicon.ico", "/**/*.png", "/**/*.gif",
//						"/**/*.svg", "/**/*.jpg", "/**/*.html", "/**/*.css",
//						"/**/*.js,/**/*")
//				.permitAll()
//				.antMatchers("/api/auth/**")
//				.permitAll().anyRequest().authenticated();
//				.antMatchers("/api/user/checkUsernameAvailability",
//						"/api/user/checkEmailAvailability").permitAll()
//				.antMatchers(HttpMethod.GET, "/synergy/api/users/")
//				.hasAnyRole("ADMIN", "RECRUITMENT_LEAD")
//				.antMatchers(HttpMethod.POST, "/synergy/api/users/")
//				.hasAnyRole("ADMIN","RECRUITMENT_LEAD","BDM","TEAM","ACCOUNT_MANAGER","USER")
//				.antMatchers(HttpMethod.PUT, "/synergy/api/users/")
//				.hasAnyRole("ADMIN","RECRUITMENT_LEAD","BDM","TEAM","ACCOUNT_MANAGER","USER")
//				.antMatchers(HttpMethod.DELETE, "/synergy/api/users/**")
//				.hasRole("ADMIN")
//				/*
//				 * .antMatchers(HttpMethod.GET, "/synergy/api/**",
//				 * "/api/users/**", "/list","/download**").permitAll()
//				 */
//				.antMatchers(HttpMethod.POST, "/upload").permitAll()
//				.antMatchers(AUTH_WHITELIST).permitAll().anyRequest()
//				.authenticated();

		// Add our custom JWT security filter
//		http.addFilterBefore(jwtAuthenticationFilter(),
//				UsernamePasswordAuthenticationFilter.class);

		        http.authorizeRequests().antMatchers("/").permitAll();
	}
}