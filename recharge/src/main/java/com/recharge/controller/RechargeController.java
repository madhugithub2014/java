package com.recharge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.recharge.service.RechargeService;
import com.recharge.vo.Customer;

@RestController
public class RechargeController {
	protected static Logger logger = LoggerFactory.getLogger(RechargeController.class.getName());
	@Autowired
	private RechargeService rechargeService;

	@RequestMapping("/")
	public String hello() {
		return rechargeService.hello();
	}

	@RequestMapping(value = "/mobile/recharge/{number}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public Customer recharge(@PathVariable("number") long mobilenum,
			@RequestBody Customer customer) {
		logger.info(String.format("Recharge.recharge(%s)", mobilenum));
		Customer cus = rechargeService.recharge(mobilenum, customer);
		logger.info(String.format("Recharge.recharge: %s", cus));
		return cus;

	}
}
