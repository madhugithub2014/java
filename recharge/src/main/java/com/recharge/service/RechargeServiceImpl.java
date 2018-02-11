package com.recharge.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.recharge.controller.RechargeController;
import com.recharge.vo.Customer;

@Service
public class RechargeServiceImpl implements RechargeService {
	
	protected static Logger logger = LoggerFactory.getLogger(RechargeServiceImpl.class.getName());

	@Autowired
	HelloClient client;
	
	@HystrixCommand(fallbackMethod="rechargeFallback")
	@Override
	public Customer recharge(long mobilenum, Customer customer) {
		logger.info(String.format("feign Customer.recharge(%s)", mobilenum));
		Customer cus = client.recharge(mobilenum, customer);
		logger.info(String.format("feign Customer.recharge: %s", cus));
		return cus;
	}
	
	public Customer rechargeFallback(long mobilenum, Customer customer){
		logger.info(String.format("fallback Customer.rechargeFallback(%s)", mobilenum));
		customer.setMessage("recharge failed...please try again...");
		logger.info(String.format("fallback Customer.rechargeFallback: %s", customer));
		return customer;
	}

	@HystrixCommand(fallbackMethod="defaultFallback")
	@Override
	public String hello() {
		return client.hello();
	}
	
	public String defaultFallback(){
		return "request failed";
	}

	@FeignClient("bank-service")
	interface HelloClient {
		@RequestMapping(value = "/", method = RequestMethod.GET)
		String hello();

		@RequestMapping(value = "/mobile/recharge/{number}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
		public Customer recharge(@PathVariable("number") long mobilenum,
				@RequestBody Customer customer);
	}
}
