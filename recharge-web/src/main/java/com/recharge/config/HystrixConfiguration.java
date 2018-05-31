package com.recharge.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;

@Configuration
@EnableAspectJAutoProxy 
public class HystrixConfiguration {
    @Bean
    public HystrixCommandAspect hystrixCommandAspect() {
        return new HystrixCommandAspect();
    }
    @Bean(name = "restTemplate")
	public RestTemplate restTemplate(final RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}
}
