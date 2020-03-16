package com.xhjc.base.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020/3/16 8:12
 **/
@Aspect
@Component
public class LoggingAspect {

    //切点重用
    @Pointcut("execution(public int com.xhjc.base.aop.ArithmeticCalculator.*(..))")
    public void declareJoinPointExpression(){

    }

    @Before("declareJoinPointExpression()")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("The method " + methodName + " begins with " + Arrays.asList(args));
    }

    @After("declareJoinPointExpression()")
    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " ends");
    }

    @AfterReturning(value = "declareJoinPointExpression()",returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " ends with " + result);
    }

    


}
