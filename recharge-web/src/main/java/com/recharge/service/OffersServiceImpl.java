package com.recharge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recharge.repository.OffersDao;
import com.recharge.vo.OffersTO;

@Service
public class OffersServiceImpl implements OffersService{
	
	@Autowired
	private OffersDao offersDao;

	public List<OffersTO> getOffers(long mobilenum) {
		List<OffersTO> offers = offersDao.getOffers(mobilenum);
		return offers;
	}
	
	public List<OffersTO> getAllOffers() {
		List<OffersTO> offers = offersDao.getAllOffers();
		return offers;
	}
}
