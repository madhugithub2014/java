package com.recharge.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recharge.service.OffersService;
import com.recharge.service.RechargeService;
import com.recharge.vo.Customer;
import com.recharge.vo.OffersTO;

@Slf4j
@RestController
@RequestMapping("/recharge/{version}/{countrycode}")
@Api(value = "RechargeController", description = "Recharge mobile")
public class RechargeController {

	@Autowired
	private OffersService offersService;

	@Autowired
	private RechargeService rechargeService;

	@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
	@GetMapping(value = "/offers", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "offers", response = OffersTO.class)
	public List<OffersTO> getAllOffers() {
		List<OffersTO> offers = offersService.getAllOffers();
		return offers;
	}

	// @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
	@GetMapping(value = "/offers/{number}", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "offers", response = OffersTO.class)
	public List<OffersTO> offers(@PathVariable("number") String mobileNum)
			throws Exception {

		validateMobileNumber(mobileNum);
		List<OffersTO> offers = offersService
				.getOffers(Long.valueOf(mobileNum));
		return offers;
	}

	@PutMapping(value = "/mobile/{number}", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "recharge for provided mobile number", response = Customer.class)
	public Customer recharge(@PathVariable("number") String mobileNum,
			@RequestBody Customer customer) {
		validateMobileNumber(mobileNum);
		// log.info("Rechage for customer={} with mobile number={}",
		// customer.getCusId(), mobilenum);
		Customer cus = rechargeService.recharge(Long.valueOf(mobileNum), customer);
		// log.info("Recharge details", cus);
		return cus;

	}

	public static  void validateMobileNumber(String mobileNum){
		
		Predicate lengthValidate = num -> Pattern.compile("\\d{10}").matcher(mobileNum).find() == true;
		Predicate numNullCheck =  num -> Optional.ofNullable(mobileNum).isPresent();
		
		if(!numNullCheck.and(lengthValidate).test(mobileNum)){
			throw new RuntimeException("please provide valid phone number");
		}else{
			System.out.println("valid mobile number..");
		}
		
	}
	}
