package com.neueda.app.linkshortener.service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

class ShortUrlServiceTest {
private ShortUrlService shortUrlService;

    @Before
    public void init() {
        shortUrlService = new ShortUrlService();
    }

    @Test
    public void makeShorter() {
    }

    @Test
    public void getOriginalUrl() {
    }

    @Test
    public void isUrlValid() {
        Assert.assertTrue(shortUrlService.isUrlValid("http://4pda.ru/"));
        Assert.assertTrue(shortUrlService.isUrlValid("https://habr.com/ru/"));
        Assert.assertTrue(shortUrlService.isUrlValid(" https://habr.com/ru/ "));
        Assert.assertFalse(shortUrlService.isUrlValid("habr.com/ru/"));
    }
}