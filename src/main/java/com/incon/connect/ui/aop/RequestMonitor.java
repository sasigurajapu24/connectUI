//package com.incon.connect.ui.aop;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//public class RequestMonitor {
//	
//    private static final Logger logger = LoggerFactory.getLogger(RequestMonitor.class);
//
//	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
//	public Object wrap(ProceedingJoinPoint pjp) throws Throwable {
//		logger.info("Before controller method " + pjp.getSignature().getName() + ". Thread " + Thread.currentThread().getName()); // toString gives more info    	
//    	Object retVal = pjp.proceed();
//	    logger.info("Controller method " + pjp.getSignature().getName() + " execution successful");
//	    return retVal;
//	}
//}
