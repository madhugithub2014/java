package com.payment.service;

import com.payment.vo.Customer;
import com.payment.vo.Offers;

public interface PaymentService {

	Customer recharge(String serviceId,long mobilenum,Customer customer);
	
	//Offers topUp(String serviceId,long mobilenum,Offers offers);
}
