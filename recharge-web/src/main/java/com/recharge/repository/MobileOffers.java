package com.recharge.repository;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.recharge.model.Offers;
import com.recharge.model.Pack;
import com.recharge.vo.OffersTO;

@Repository
public class MobileOffers implements OffersDao {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<OffersTO> getAllOffers() {
		
		List<Offers> offers = mongoTemplate.findAll(Offers.class);
		String currency = "₹";
		List<OffersTO> offerList = new ArrayList<>();
		for (Offers offer : offers) {
			OffersTO offersTO = new OffersTO();
			offersTO.setNames(offer.getNames());
			offersTO.setDescription(offer.getDescription());
			for(Pack pack:offer.getPack()){				
		        pack.setCurrencyAmt(currency+" "+pack.getAmount());
			}
			offersTO.setPack(offer.getPack());
			offerList.add(offersTO);
		}
		return offerList;
	}

	@Override
	public List<OffersTO> getOffers(long mobilenum) {
		// TODO Auto-generated method stub
		return null;
	}

}
