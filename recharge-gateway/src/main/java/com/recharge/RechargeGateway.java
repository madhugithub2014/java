package com.recharge;

import java.net.MalformedURLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


@SpringBootApplication
@EnableZuulProxy
@EnableOAuth2Sso
public class RechargeGateway {

    public static void main(String[] args) throws MalformedURLException {

//        Object[] sources = {SpringCloudZuulExampleApplication.class, new UrlResource(new URL("file:C:/dev/abc_dynamic.groovy"))};
       // Object[] sources = {RechargeGateway.class, new ClassPathResource("ExampleSurgicalDebugFilterBean.groovy")};
        SpringApplication.run(RechargeGateway.class, args);
        //new SpringApplicationBuilder(sources).web(true).run(args);

    }
    
    @Bean
    public ExampleSurgicalDebugFilterBean getExampleSurgicalDebugFilterBean(){
    	return new ExampleSurgicalDebugFilterBean();
    }
    
    @Configuration
	@EnableResourceServer
	static class ResourceServer extends ResourceServerConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.requestMatchers().and().authorizeRequests().antMatchers(HttpMethod.GET, "/recharge/**")
					.access("#oauth2.hasScope('bharati_offers') and (hasRole('ROLE_ADMIN') or hasRole('ROLE_USER'))")
					.antMatchers(HttpMethod.PUT, "/recharge/**")
					.access("#oauth2.hasScope('bharati_recharge') and hasRole('ROLE_USER')");
		}
	}

}
