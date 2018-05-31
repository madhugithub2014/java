package com.payment.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.payment.vo.Offers;

@Slf4j
@Service
public class RechargeofferServiceImpl implements RechargeOfferService {

	@Override
	public Offers offers(String serviceId, long mobilenum) {
		Offers offers = new Offers();
		offers.setAmount(200);
		offers.setDescription("1. Rs.200 -> 10GB + Free voice calls for 30 days");
		return offers;
		//log.info("Available offers for the mob={}", mobilenum);
	}
	
	

}
