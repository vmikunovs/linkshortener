package com.neueda.app.linkshortener.controller;

import com.neueda.app.linkshortener.domain.shortUrl.ShortUrl;
import com.neueda.app.linkshortener.service.ShortUrlService;
import com.neueda.app.linkshortener.service.statistic.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.transaction.Transactional;

@RestController()
@RequestMapping("/short")
public class ShortUrlController {
    private static final Logger log = LoggerFactory.getLogger(ShortUrlController.class);

    private ShortUrlService shortUrlService;

    @Autowired
    public void setShortUrlService(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @GetMapping("/{uuid}")
    public RedirectView getOriginalUrl(@PathVariable String url) {
        return new RedirectView(shortUrlService.getOriginalUrl(url));
    }

    @Transactional
    @PostMapping
    public ShortUrl makeShortUrl(@RequestBody String url) {
        return shortUrlService.makeShorter(url);
    }
}
