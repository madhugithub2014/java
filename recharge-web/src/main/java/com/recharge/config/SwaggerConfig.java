package com.recharge.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author c003933
 * Swagger Configuration File
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()                 
				.apis(RequestHandlerSelectors.basePackage("com.recharge.controller"))
				.paths(regex("/recharge/.*"))
				.build()
				.apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo(
				"Welcome to recharge Microservice",
				"One stop repository for recharge corresponding to mobile number and country code",
				"1.0",
				"Terms of service",
				new Contact("MadhuTech", "http://madhutech.in", "email@madhutech.com"),
				"Apache License Version 2.0",
				"https://www.apache.org/licenses/LICENSE-2.0");
		return apiInfo;
	}
}
