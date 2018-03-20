package org.kx.nick;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Slf4j
@Component
public class DaoAspect {

    @Around("execution(public * org.kx.nick.*Dao.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return AspectUtil.logAround(joinPoint , 500L);
    }
}
