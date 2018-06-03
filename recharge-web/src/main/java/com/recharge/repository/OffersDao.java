package com.recharge.repository;

import java.util.List;

import com.recharge.vo.OffersTO;

public interface OffersDao {
	List<OffersTO> getAllOffers();
	
	List<OffersTO> getOffers(long mobilenum);
}
