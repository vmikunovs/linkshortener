package com.neueda.app.linkshortener.controller;

import com.neueda.app.linkshortener.domain.shortUrl.ShortUrl;
import com.neueda.app.linkshortener.service.ShortUrlService;
import com.neueda.app.linkshortener.service.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController()
@RequestMapping("/api")
public class ShortUrlController {
    private static final Logger log = LoggerFactory.getLogger(ShortUrlController.class);

    private StatisticService statisticService;
    private ShortUrlService shortUrlService;

    @Autowired
    public void setShortUrlService(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @Autowired
    public void setStatisticService(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("{url}")
    public RedirectView getOriginalUrl(@PathVariable String url) {
        return new RedirectView(shortUrlService.getOriginalUrl(url));
    }

    @PostMapping
    public ShortUrl makeShortUrl(@RequestBody String url) {
        return shortUrlService.makeShorter(url);
    }
/*
    @GetMapping("statistic")
    public ShortUrlStatistic getStatistic() {
        return statisticService.getStatistic();
    }

    public String makeShorterUrl(String url) {
        try {
           return shortUrlService.makeShorter(url);
        } catch (Exception e) {
            return  "";
        }
    }*/
}
