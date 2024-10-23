package com.estsoft.springproject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeLoggingAop {
    // 특정 method() 호출했을때, method의 수행 시간 측정
    // @PointCut 사용해도 괜찮음
    @Around("execution(* com.estsoft.springproject.book..*(..))")    // .*(..) 메서드 표현식,
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());
        try{
            return joinPoint.proceed();
        } finally {
            long finishTimeMs = System.currentTimeMillis();
            long timeMs = finishTimeMs - startTime;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }
}
