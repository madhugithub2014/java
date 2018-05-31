package com.recharge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recharge.service.RechargeService;
import com.recharge.vo.Customer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/recharge/{version}/{countrycode}")
@Api(value = "RechargeController", description = "Recharge mobile")
public class RechargeController {

	@Autowired
	private RechargeService rechargeService;

	@GetMapping(value = "/offers/{number}")
	@ApiOperation(value = "offers", response = String.class)
	public String offers(@PathVariable("number") long mobilenum) {
		return rechargeService.getOffers(mobilenum);
	}

	@PutMapping(value = "/mobile/{number}", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "recharge for provided mobile number", response = Customer.class)
	public Customer recharge(@PathVariable("number") long mobilenum, @RequestBody Customer customer) {
		log.info("Rechage for customer={} with mobile number={}", customer.getCusId(), mobilenum);
		Customer cus = rechargeService.recharge(mobilenum, customer);
		log.info("Recharge details", cus);
		return cus;

	}
}
