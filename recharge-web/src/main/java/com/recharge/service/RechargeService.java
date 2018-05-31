package com.recharge.service;

import com.recharge.vo.Customer;
import com.recharge.vo.Offers;

public interface RechargeService {

	Offers getOffers(long mobilenum);
	//Offers topUp(long mobilenum,Offers offers);
	Customer recharge(long mobilenum,Customer customer);
}
