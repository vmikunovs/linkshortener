package com.neueda.app.linkshortener.aspect;

import com.neueda.app.linkshortener.domain.shortUrl.ShortUrl;
import com.neueda.app.linkshortener.service.statistic.StatisticService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StatisticsAspect {

    private StatisticService statisticService;

    @Autowired
    public void setStatisticService(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @AfterReturning(pointcut = "execution(* com.neueda.app.linkshortener.service.ShortUrlService.makeShorter(..))", returning="returnValue")
    public void makeShorterUrl(JoinPoint jp, ShortUrl returnValue) {
        statisticService.logMakeShortUrl(returnValue.getOriginalUrl());
    }

}
