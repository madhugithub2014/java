package com.recharge;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.recharge.RechargeServer;
import com.recharge.service.OffersService;
import com.recharge.vo.OffersTO;

@SpringBootTest(classes=RechargeServer.class)
public class MobileOffersServiceTest {
	
	private static final String MOBILENUMBER = "9916441110";

	@Autowired
	private OffersService mobileOffersService;
	
	@Test
	public void tarrifPacks(){
	/*	OffersTO offer = mobileOffersService.getTarrifPacks(MOBILENUMBER);
		Assert.assertEquals(200, offer.getAmount());
		Assert.assertNotNull(offer.getDescription());*/
	}
}
