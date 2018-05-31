package com.recharge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableHystrix
@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class RechargeServer {
	public static void main(String[] args) {
		final String classMethodName = "RechargeServer";
		final String defaultConfigLocation = "/application/config/recharge/";
		log.info("{}|Starting RechargeServer with default location for spring config file at [{}]", classMethodName,
				defaultConfigLocation);
		System.setProperty("spring.config.location", defaultConfigLocation);
		SpringApplication.run(RechargeServer.class, args);
	}

	@Configuration
	@EnableResourceServer
	static class ResourceServer extends ResourceServerConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.requestMatchers().and().authorizeRequests().antMatchers(HttpMethod.GET, "/recharge/**")
					.access("#oauth2.hasScope('read_recharge_offer') and (hasRole('ROLE_ADMIN') or hasRole('ROLE_USER'))")
					.antMatchers(HttpMethod.PUT, "/recharge/**")
					.access("#oauth2.hasScope('write_recharge') and hasRole('ROLE_USER')");
		}
	}
}