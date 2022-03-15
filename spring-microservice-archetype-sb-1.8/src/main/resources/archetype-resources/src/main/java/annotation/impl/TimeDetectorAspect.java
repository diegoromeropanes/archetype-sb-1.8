package ${package}.annotation.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class TimeDetectorAspect {

    @Around("@annotation(${package}.annotation.TimeDetector)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Long timeInit = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();
        Long tiempoEjecucion = (System.currentTimeMillis() - timeInit);
        log.info(joinPoint.getSignature().getName() + " Tiempo de ejecucion:" + tiempoEjecucion);
        return proceed;
    }
}
