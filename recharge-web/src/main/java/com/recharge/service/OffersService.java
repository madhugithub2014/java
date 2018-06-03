package com.recharge.service;

import java.util.List;

import com.recharge.vo.OffersTO;

public interface OffersService {
	List<OffersTO> getOffers(long mobilenum);
	
	List<OffersTO> getAllOffers();

	default OffersTO getTarrifPacks(String MobileNumber) {
		return null;
	}
}
