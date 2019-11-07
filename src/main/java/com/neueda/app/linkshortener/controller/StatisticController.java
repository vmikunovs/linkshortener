package com.neueda.app.linkshortener.controller;

import com.neueda.app.linkshortener.domain.statistics.ShortUrlStatistic;
import com.neueda.app.linkshortener.service.statistic.StatisticModel;
import com.neueda.app.linkshortener.service.statistic.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private StatisticService statisticService;

    @Autowired
    public void setStatisticService(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping
    public StatisticModel getStatistic() {
        return statisticService.getStatistic();
    }

    @GetMapping("/top/{count}")
    public List<ShortUrlStatistic> getTopOfShortUrl(@PathVariable Integer count) {
        log.info("call the top of the first {} positions", count);
        return statisticService.getTop(count);
    }

    @GetMapping("/all")
    public List<ShortUrlStatistic> fullStatistics() {
        return statisticService.getFullStatistic();
    }
}
