package com.recharge;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.recharge.service.MobileOffersService;
import com.recharge.vo.Offers;

import junit.framework.Assert;

@SpringBootTest(classes=RechargeServer.class)
public class MobileOffersServiceTest {
	
	private static final String MOBILENUMBER = "9916441110";

	@Autowired
	private MobileOffersService mobileOffersService;
	
	@Test
	public void tarrifPacks(){
		Offers offer = mobileOffersService.getTarrifPacks(MOBILENUMBER);
		Assert.assertEquals(200, offer.getAmount());
		Assert.assertNotNull(offer.getDescription());
	}
}
