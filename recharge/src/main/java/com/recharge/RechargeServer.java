package com.recharge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableHystrix
@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class RechargeServer {
	public static void main(String[] args) {
		final String classMethodName="RechargeServer";
		final String defaultConfigLocation = "/application/config/recharge/";
		log.info("{}|Starting RechargeServer with default location for spring config file at [{}]", classMethodName,defaultConfigLocation);
		System.setProperty("spring.config.location", defaultConfigLocation);
		SpringApplication.run(RechargeServer.class, args);
	}
}