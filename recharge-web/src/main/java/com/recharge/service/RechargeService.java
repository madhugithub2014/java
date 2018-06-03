package com.recharge.service;

import com.recharge.vo.Customer;

public interface RechargeService {

	Customer recharge(long mobilenum,Customer customer);
}
