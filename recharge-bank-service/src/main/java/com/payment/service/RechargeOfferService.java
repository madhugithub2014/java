package com.payment.service;

import com.payment.vo.Offers;

public interface RechargeOfferService {
	Offers offers(String serviceId,long mobilenum);	
}
