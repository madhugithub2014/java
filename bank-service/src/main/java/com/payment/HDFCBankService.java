package com.payment;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.payment.vo.Customer;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class HDFCBankService {
	@Autowired
	DiscoveryClient client;

	@RequestMapping("/")
	public String hello() {
		ServiceInstance localInstance = client.getLocalServiceInstance();
		return "Hello World: " + localInstance.getServiceId() + ":"
				+ localInstance.getHost() + ":" + localInstance.getPort();
	}

	@RequestMapping(value = "/mobile/recharge/{number}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public Customer mobileService(@PathVariable("number") String mobilenum,
			@RequestBody Customer customer) {
		customer.setMessage("Recharge successful");
		Random random = new Random();
		String transactionId = String.valueOf(random.hashCode());
		customer.setService("1st instance");
		customer.setTransactionId(transactionId);
		return customer;
	}

	public static void main(String[] args) {
		SpringApplication.run(HDFCBankService.class, args);
	}
}
