package com.recharge.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Configuration
@Aspect
public class GuidAspect {
	/**
	 * Intercepts the controller and service calls to record their time
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.recharge.controller.*.*(..)) || execution(* com.recharge.service.*.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		long methodStartTime = System.currentTimeMillis();
		String methodName = null;
		String simpleClassName = null;
		try {
			methodName = joinPoint.getSignature().getName();
			simpleClassName = joinPoint.getTarget().getClass().getSimpleName();
			return joinPoint.proceed();
		}catch(Throwable b) {
			log.error(simpleClassName+"."+methodName+"|"+(System.currentTimeMillis() - methodStartTime)+"| Error while executing methood", b);
			throw b;
		}
		finally {
			log.info("{}.{}|{}|Method completed", simpleClassName,methodName,System.currentTimeMillis() - methodStartTime);
		}
	}
}
