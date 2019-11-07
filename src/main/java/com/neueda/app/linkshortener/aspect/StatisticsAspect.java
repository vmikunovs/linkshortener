package com.neueda.app.linkshortener.aspect;

import com.neueda.app.linkshortener.domain.shortUrl.ShortUrl;
import com.neueda.app.linkshortener.service.StatisticsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StatisticsAspect {

    private StatisticsService statisticsService;

    @Autowired
    public void setStatisticsService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @AfterReturning(pointcut = "execution(* com.neueda.app.linkshortener.service.ShortUrlService.makeShorter(..))", returning="returnValue")
    public void makeShorterUrl(JoinPoint jp, ShortUrl returnValue) {
        statisticsService.logMakeShortUrl(returnValue.getUuid());
    }

    @After("execution(* com.neueda.app.linkshortener.controller.ShortUrlController.getOriginalUrl(..))")
    public void redirectAmount(JoinPoint jp) {
       statisticsService.logRedirect((String) jp.getArgs()[0]);
    }
}
