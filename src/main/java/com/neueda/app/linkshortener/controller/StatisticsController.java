package com.neueda.app.linkshortener.controller;

import com.neueda.app.linkshortener.domain.statistics.ShortUrlStatisticModel;
import com.neueda.app.linkshortener.service.StatisticsService;
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
public class StatisticsController {
    private static Logger log = LoggerFactory.getLogger(StatisticsController.class);

    private StatisticsService statisticsService;

    @Autowired
    public void setStatisticsService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/top/creationCount/{count}")
    public List<ShortUrlStatisticModel> getTopOfShortUrlLinkCreation(@PathVariable Integer count) {
        log.info("call getTopOfShortUrlLinkCreation {} positions", count);
        return statisticsService.getTopLinkCreation(count);
    }

    @GetMapping("/top/redirectAmount/{count}")
    public List<ShortUrlStatisticModel> getTopOfShortUrlRedirect(@PathVariable Integer count) {
        log.info("call getTopOfShortUrlRedirect {} positions", count);
        return statisticsService.getTopLinkRedirect(count);
    }

    @GetMapping("/all")
    public List<ShortUrlStatisticModel> fullStatistics() {
        log.info("call fullStatistics");
        return statisticsService.getFullStatistic();
    }
}
