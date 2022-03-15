package ${package}.annotation.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class AnnotationAspect {
	
	@Around("@annotation(${package}.annotation.Annotation)")
	public Object annotation(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        
        log.info("Ejecucion annotation");
        return proceed;
    }
	
}
