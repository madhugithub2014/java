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
import com.recharge.vo.Offers;

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

	@HystrixCommand(fallbackMethod = "offersFallback")
	public Offers getOffers(long mobilenum) {
		Offers offers = client.getOffers(serviceId, mobilenum);
		return offers;
	}

	public Offers offersFallback(long mobilenum) {
		Offers offers = new Offers();
		offers.setAmount(200);
		offers.setDescription("try again");
		return offers;
	}

	/*@HystrixCommand(fallbackMethod = "topUpFallback")
	public Offers topUp(long mobilenum,Offers offers) {
		Offers offer = client.topUp(serviceId, mobilenum,offers);
		return offer;
	}

	public Offers topUpFallback(long mobilenum,Offers offers) {
		offers.setDescription("failed");
		return offers;
	}*/

	@FeignClient("bank-service")
	interface PaymentClient {
		@RequestMapping(value = "/payment/offers/{serviceid}/{number}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
		Offers getOffers(@PathVariable("serviceid") String serviceId,
				@PathVariable("number") long mobilenum);

		@RequestMapping(value = "/payment/mobile/{serviceid}/{number}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
		Customer recharge(@PathVariable("serviceid") String serviceId,
				@PathVariable("number") long mobilenum,
				@RequestBody Customer customer);
		
/*		@RequestMapping(value = "/payment/topup/{serviceid}/{number}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
		Offers topUp(@PathVariable("serviceid") String serviceId,
				@PathVariable("number") long mobilenum,@RequestBody Offers offers);
*/	}
}
