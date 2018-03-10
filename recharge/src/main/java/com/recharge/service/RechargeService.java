package com.recharge.service;

import com.recharge.vo.Customer;

public interface RechargeService {

	String getOffers(long mobilenum);
	Customer recharge(long mobilenum,Customer customer);
}
