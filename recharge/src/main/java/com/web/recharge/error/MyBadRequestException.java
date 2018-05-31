package com.recharge.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(MyBadRequestException.class.getName());
	
	MyBadRequestException(){
		logger.warn("Exception while getting response:>=400 && <=499");
	}

}
