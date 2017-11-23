package com.emhc;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class StudentWebConfig extends WebMvcConfigurerAdapter {
	
	private static final String[] MESSAGESOURCE_BASENAME = {"classpath:/i18n/messages", "classpath:/i18n/validationmessages"};
	//private static final String MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE = "message.source.use.code.as.default.message";

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

	}
	
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		final PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		
		Resource[] resources = new ClassPathResource[]{ new ClassPathResource("application.properties")};
		
        pspc.setLocations(resources);
        pspc.setIgnoreResourceNotFound(true);
        pspc.setIgnoreUnresolvablePlaceholders(true);
        pspc.setLocalOverride(true);
		return pspc;
	}
	
	
    @Bean(name ="messageSource")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        
        //messageSource.setBasename("classpath:/i18n/messages");
        messageSource.setBasenames(MESSAGESOURCE_BASENAME);
        //messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        
        return messageSource;
    }
    
    
    @Bean
    public LocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		resolver.setCookieName("midwiferyLocaleCookie");
		resolver.setCookieMaxAge(4800);
		return resolver;
    }
    
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		registry.addInterceptor(interceptor);
    }
    
    
    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(getMessageSource());
        return bean;
    }
    
    
    @Override
    public Validator getValidator() {
        return validator();
    }
    
}
