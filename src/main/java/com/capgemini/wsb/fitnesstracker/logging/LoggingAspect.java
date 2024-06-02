package com.capgemini.wsb.fitnesstracker.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Aspekt LoggingAspect służy do logowania działania metod w serwisach.
 * Loguje informacje przed i po wykonaniu publicznych metod w klasach oznaczonych adnotacją {@link org.springframework.stereotype.Service}.
 *
 * @author Wiktor Marciniak
 * @version 1.0
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Loguje informację przed wykonaniem każdej publicznej metody w serwisach.
     * Format loga to 'Before method: typZwracany nazwaKlasy.nazwaMetody(typParametru nazwaParametru, ...)'.
     *
     * @param joinPoint dostarcza informacji kontekstowych o przeciętym punkcie wykonania.
     */
    @Before("execution(public * com.capgemini.wsb.fitnesstracker..*.*(..)) && within(@org.springframework.stereotype.Service *)")
    public void logMethodCall(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.toShortString();
        logger.info("Before method: " + methodName);
    }

    /**
     * Loguje informację po wykonaniu metody w serwisach.
     * Format loga to 'After method: typZwracany nazwaKlasy.nazwaMetody(typParametru nazwaParametru, ...) returned: wartośćZwracana'.
     *
     * @param joinPoint dostarcza informacji kontekstowych o przeciętym punkcie wykonania.
     * @param result obiekt zwrócony przez przeciętą metodę.
     */
    @AfterReturning(pointcut = "execution(public * com.capgemini.wsb.fitnesstracker..*.*(..)) && within(@org.springframework.stereotype.Service *)", returning = "result")
    public void logMethodReturn(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.toShortString();
        logger.info("After method: " + methodName + " returned: " + result);
    }
}
