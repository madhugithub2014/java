package com.recharge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.recharge.vo.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RechargeServiceImpl implements RechargeService {
	private static final String serviceId = "Bharati";

	@Autowired
	PaymentClient client;

	@HystrixCommand(fallbackMethod = "rechargeFallback")
	public Customer recharge(long mobilenum, Customer customer) {
		log.info("Customer.recharge(%s)", mobilenum);
		Customer cus = client.recharge(serviceId, mobilenum, customer);
		log.info("Customer.recharge for customer{}", cus);
		return cus;
	}

	public Customer rechargeFallback(long mobilenum, Customer customer) {
		log.info("fallback Customer.rechargeFallback for number={}", mobilenum);
		customer.setMessage("recharge failed...please try again...");
		log.info("fallback Customer.rechargeFallback for customer={}", customer);
		return customer;
	}

	@HystrixCommand(fallbackMethod = "offersFallback")
	public String getOffers(long mobilenum) {
		return client.offers(serviceId, mobilenum);
	}

	public String offersFallback(long mobilenum) {
		return "Please try again..";
	}

	@FeignClient("bank-service")
	interface PaymentClient {
		@RequestMapping(value = "/offers/{serviceid}/{number}", method = RequestMethod.GET)
		String offers(@PathVariable("serviceid") String serviceId, @PathVariable("number") long mobilenum);

		@RequestMapping(value = "/mobile/{serviceid}/{number}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
		public Customer recharge(@PathVariable("serviceid") String serviceId, @PathVariable("number") long mobilenum,
				@RequestBody Customer customer);
	}
}
