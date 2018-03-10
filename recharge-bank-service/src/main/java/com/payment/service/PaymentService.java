package com.payment.service;

import com.payment.vo.Customer;

public interface PaymentService {

	Customer recharge(String serviceId,long mobilenum,Customer customer);
}
