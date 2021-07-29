package com.kucom.simsim;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class logAspect {
	private static final Logger logger = LoggerFactory.getLogger(logAspect.class);
	
	@Around("@annotation(logExecutionTime)")
	public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object proceed = joinPoint.proceed();	
		
		stopWatch.stop();
		logger.info(stopWatch.prettyPrint());
		
		return proceed;
	}
}
