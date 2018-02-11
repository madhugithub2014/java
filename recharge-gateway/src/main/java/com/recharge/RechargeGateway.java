package com.recharge;

import java.net.MalformedURLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


@EnableZuulProxy
@SpringBootApplication
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

}
