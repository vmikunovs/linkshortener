package com.neueda.app.linkshortener.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StatisticsOperator {
    @Pointcut("execution(* com.neueda.app.linkshortener.service.ShortUrlService.makeShorter(..))")
    public void makeShorter(){}//pointcut name

    @After("makeShorter()")//applying pointcut on before advice
    public void makeShorterUrl (JoinPoint jp)//it is advice (before advice)
    {

        System.out.println("additional concern");
        //System.out.println("Method Signature: "  + jp.getSignature());
    }

}
