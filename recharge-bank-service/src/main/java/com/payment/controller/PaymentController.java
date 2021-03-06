package com.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payment.service.PaymentService;
import com.payment.service.RechargeOfferService;
import com.payment.vo.Customer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(value = "PaymentController", description = "Payment Gateway")
public class PaymentController {
	@Autowired
	DiscoveryClient client;

	@Autowired
	PaymentService paymentService;

	@Autowired
	RechargeOfferService rechargeOfferService;

	@GetMapping("/health")
	@ApiOperation(value = "health", response = String.class)
	public String getInstaceInfo(@PathVariable("serviceid") String serviceId, @PathVariable("number") long mobilenum) {
		ServiceInstance localInstance = client.getLocalServiceInstance();
		return "Health: " + localInstance.getServiceId() + ":" + localInstance.getHost() + ":"
				+ localInstance.getPort();
	}

	@GetMapping("/offers/{serviceid}/{number}")
	@ApiOperation(value = "offers", response = String.class)
	public String getOffers(@PathVariable("serviceid") String serviceId, @PathVariable("number") long mobilenum) {
		log.info("Offers for the mob={}",mobilenum);
		return rechargeOfferService.offers(serviceId, mobilenum);
	}

	@PutMapping(value = "/mobile/{serviceid}/{number}", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "payment for provided mobile number", response = Customer.class)
	public Customer mobileService(@PathVariable("serviceid") String serviceId, @PathVariable("number") long mobilenum,
			@RequestBody Customer customer) {
		log.info("Payment processing...please wait..do not refresh..mob:{}", mobilenum);
		return paymentService.recharge(serviceId, mobilenum, customer);
	}
}
