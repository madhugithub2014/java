package com.recharge.service;

import com.recharge.vo.Customer;

public interface RechargeService {

	String hello();
	Customer recharge(long mobilenum,Customer customer);
}
