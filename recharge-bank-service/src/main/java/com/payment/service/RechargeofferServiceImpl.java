package com.payment.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RechargeofferServiceImpl implements RechargeOfferService {

	@Override
	public String offers(String serviceId, long mobilenum) {
		String offerPack = "Offers: from "+serviceId+" 1. Rs.200 -> 10GB + Free voice calls for 30 days";
		log.info("Available offers for the mob={}", mobilenum);
		return offerPack;
	}

}
