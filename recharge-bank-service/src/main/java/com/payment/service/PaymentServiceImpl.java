package com.payment.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.payment.vo.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

	@Override
	public Customer recharge(String serviceId, long mobilenum,Customer customer) {
		customer.setMessage("Recharge successful");
		Random random = new Random();
		String transactionId = String.valueOf(random.hashCode());
		customer.setService("1st instance");
		customer.setService(serviceId);
		customer.setService(serviceId);
		customer.setTransactionId(transactionId);
		log.info("Payment successful for the mob:{}",mobilenum);
		return customer;
	}

}
