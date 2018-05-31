package com.recharge.service;

import com.recharge.vo.Offers;

public interface MobileOffersService {
default Offers getTarrifPacks(String MobileNumber){
	return null;	
}
}
