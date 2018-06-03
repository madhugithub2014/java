package com.recharge.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.recharge.vo.Customer;
import com.recharge.vo.OffersTO;

@Slf4j
@Service
public class RechargeServiceImpl implements RechargeService {
	private static final String serviceId = "Bharati";

	@Autowired
	PaymentClient client;

	@HystrixCommand(fallbackMethod = "rechargeFallback")
	public Customer recharge(long mobilenum, Customer customer) {
		// log.info("Customer.recharge(%s)", mobilenum);
		Customer cus = client.recharge(serviceId, mobilenum, customer);
		// log.info("Customer.recharge for customer{}", cus);
		return cus;
	}

	public Customer rechargeFallback(long mobilenum, Customer customer) {
		// log.info("fallback Customer.rechargeFallback for number={}",
		// mobilenum);
		customer.setMessage("recharge failed...please try again...");
		// log.info("fallback Customer.rechargeFallback for customer={}",
		// customer);
		return customer;
	}

	@FeignClient("bank-service")
	interface PaymentClient {
		@RequestMapping(value = "/payment/mobile/{serviceid}/{number}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
		Customer recharge(@PathVariable("serviceid") String serviceId,
				@PathVariable("number") long mobilenum,
				@RequestBody Customer customer);

	}
}
