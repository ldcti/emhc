package com.emhc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.emhc.security.CustomSuccessHandler;
import com.emhc.security.StudentAuthenticationProvider;
import com.emhc.security.StudentUserService;


@Configuration
public class MultiHttpSecurityConfig {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
	StudentUserService studentUserService;
	
	@Autowired
	static CustomSuccessHandler customSuccessHandler;

	
	@Configuration
	public static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			System.out.println("/student/login/ configure");
			http.
				authorizeRequests()
	            .antMatchers("/", "/student/login/**").permitAll()
	            //.antMatchers("/student/registration").permitAll()
	        	.antMatchers("/error/**").permitAll()
	        	.antMatchers("/image/**").permitAll()
	        	.antMatchers("/css/**").permitAll()
	            //.antMatchers("/student/").permitAll()
	         	.antMatchers("/student/login/**").permitAll()
	        	.antMatchers("/student/**").hasAuthority("CLIENT")
	            .anyRequest().authenticated()
	        .and()//.csrf().disable()
		        .formLogin()
		            .loginPage("/student/login")
		            //.successHandler(customSuccessHandler)
		            .failureUrl("/login?error=true")
		            .usernameParameter("username")
		            .defaultSuccessUrl("/student/home")
		            .permitAll()
	        .and()
	        	.logout()
		            .logoutUrl("/student/logout")
		            .deleteCookies("remember-me")
		            .logoutSuccessUrl("/student/login")
		            .permitAll()
	        .and()
				.rememberMe();
		}
	}

	
	@Configuration
	@Order(1)
	public static class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			System.out.println("/admin/login/ configure");
			http
				.antMatcher("/admin/**")
				.authorizeRequests()
	         	.antMatchers("/admin/login/**").permitAll()
	        	.antMatchers("/admin/**").hasAuthority("ADMIN")
	                .anyRequest().authenticated()
	        .and()
		        .formLogin()
		            .loginPage("/admin/login")
		            //.successHandler(customSuccessHandler)
		            .usernameParameter("username")
		            .defaultSuccessUrl("/admin/home")
		            .permitAll()
	        .and()
		        .logout()
		        	.logoutSuccessUrl("/admin/login")
		            .permitAll();
		}
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(studentUserService);
		auth.authenticationProvider(getAuthProvider());
	}

    @Bean
    public DaoAuthenticationProvider getAuthProvider() {
        final StudentAuthenticationProvider authProvider = new StudentAuthenticationProvider();
        authProvider.setUserDetailsService(studentUserService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authProvider;
    }
}
