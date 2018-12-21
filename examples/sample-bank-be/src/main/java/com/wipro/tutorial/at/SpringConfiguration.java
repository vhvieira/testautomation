package com.wipro.tutorial.at;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = { "com.wipro.tutorial.at" })
@PropertySource("classpath:configs/environment.properties")
public class SpringConfiguration {

	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(getHttpMessageConverters());
		return restTemplate;
	}

	@Bean
	public PropertyPlaceholderConfigurer getPropertyConfigurer() {
		PropertyPlaceholderConfigurer p = new PropertyPlaceholderConfigurer();
		p.setLocation(new ClassPathResource("configs/environment.properties"));
		p.setIgnoreUnresolvablePlaceholders(true);
		return p;
	}

	@Bean
	public List<HttpMessageConverter<?>> getHttpMessageConverters() {
		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2HttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		return converters;
	}
}