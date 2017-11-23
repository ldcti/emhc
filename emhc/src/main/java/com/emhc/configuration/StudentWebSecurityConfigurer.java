package com.emhc.configuration;

//import javax.inject.Inject;

//import org.baeldung.security.google2fa.CustomAuthenticationProvider;
//import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.emhc.security.CustomSuccessHandler;
import com.emhc.security.StudentAuthenticationProvider;
import com.emhc.security.StudentUserService;


//@Configuration
//@EnableWebSecurity
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class StudentWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    //private static final String CAS_URL_LOGIN = "cas.service.url.login";
    //private static final String CAS_URL_LOGOUT = "cas.service.url.logout";
    //private static final String CAS_URL_PREFIX = "cas.service.url.prefix";
    //private static final String CAS_SERVICE_URL = "app.service.security";
    //private static final String APP_SERVICE_HOME = "app.service.home";

    //@Inject
    //private Environment env;

    @Autowired
	StudentUserService studentUserService;
	
	@Autowired
	CustomSuccessHandler customSuccessHandler;
	
	
   /* @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties sp = new ServiceProperties();
        sp.setService(env.getRequiredProperty(CAS_SERVICE_URL));
        sp.setSendRenew(false);
        return sp;
    }*/

    /*@Bean
    public RememberCasAuthenticationProvider casAuthenticationProvider() {
        RememberCasAuthenticationProvider casAuthenticationProvider = new RememberCasAuthenticationProvider();
        casAuthenticationProvider.setAuthenticationUserDetailsService(userDetailsService);
        casAuthenticationProvider.setServiceProperties(serviceProperties());
        casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
        casAuthenticationProvider.setKey("an_id_for_this_auth_provider_only");
        return casAuthenticationProvider;
    }*/

    /*@Bean
    public SessionAuthenticationStrategy sessionStrategy() {
        SessionFixationProtectionStrategy sessionStrategy = new CustomSessionFixationProtectionStrategy();
        sessionStrategy.setMigrateSessionAttributes(false);
        // sessionStrategy.setRetainedAttributes(Arrays.asList("CALLBACKURL"));
        return sessionStrategy;
    }*/

    /*@Bean
    public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
        return new Cas20ServiceTicketValidator(env.getRequiredProperty(CAS_URL_PREFIX));
    }*/

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	try{
    		
			System.out.println("------run to StudentWebSecurityConfigurer-----");
    		
        http.authorizeRequests()
        	.antMatchers("/").permitAll()
        	.antMatchers("/service/runlottery/**").permitAll()
        	.antMatchers("/error/**").permitAll()
        	.antMatchers("/image/**").permitAll()
        	.antMatchers("/css/**").permitAll()
        	.antMatchers("/student/template/**").permitAll()
         	.antMatchers("/student/login/**").permitAll()
         	.antMatchers("/student/registration/**").permitAll()
        	.antMatchers("/student/**").hasAuthority("CLIENT")
       	.and()
            .formLogin()
            .loginPage("/student/login").successHandler(customSuccessHandler)
            .failureUrl("/student/login?error")
            .usernameParameter("username")
            .permitAll()
        .and()
            .logout()
            .logoutUrl("/student/logout")
            .deleteCookies("remember-me")
            .logoutSuccessUrl("/student/login")
            .permitAll()
        .and()
			.rememberMe()
			;
    	}
		catch(Exception e){
			e.printStackTrace();
			
		}
    }
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(studentUserService); //un-encrypted password
        //auth.userDetailsService(studentUserService).passwordEncoder(new BCryptPasswordEncoder());
		auth.userDetailsService(studentUserService);
		auth.authenticationProvider(getAuthProvider());
    }

    @Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/i18n/**")
		.antMatchers("/static/**");  
	}
    
    @Bean
    public DaoAuthenticationProvider getAuthProvider() {
        final StudentAuthenticationProvider authProvider = new StudentAuthenticationProvider();
        authProvider.setUserDetailsService(studentUserService);
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authProvider;
    }
    
}
