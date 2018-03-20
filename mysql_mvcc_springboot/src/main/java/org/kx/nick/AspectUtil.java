package org.kx.nick;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;


@Slf4j
public class AspectUtil {

    public static Object logAround(ProceedingJoinPoint joinPoint, long maxTimeInMillis) throws Throwable {
        long start = System.currentTimeMillis();
        String methodName = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();
        log.info("{}开始", methodName);
        StringBuilder methodInfo = new StringBuilder(methodName);
        if(joinPoint.getArgs() != null) {
            methodInfo.append("(").append(Arrays.asList(joinPoint.getArgs())).append(")");
        }
        log.info("方法请求参数 : {}" , methodInfo.toString());
        boolean success = false;
        Object e;
        try {
            success = true;
            e = joinPoint.proceed();
            success = false;
        } catch (Throwable t) {
            log.error("方法{}调用异常{}" , methodInfo.toString(),t);
            throw t;
        } finally {
            long timeUsed = System.currentTimeMillis() - start;
            if(success) {
                if(timeUsed > maxTimeInMillis) {
                    log.warn("{}结束, 所花时间: {}ms", methodName, timeUsed);
                }
            }
        }
        long methodInfo1 = System.currentTimeMillis() - start;
        if(methodInfo1 > maxTimeInMillis) {
            log.warn("{}结束, 所花时间: {}ms", methodName, methodInfo1);
        }
        return e;
    }
}
