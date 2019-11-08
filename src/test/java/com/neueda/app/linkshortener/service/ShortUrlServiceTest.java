package com.neueda.app.linkshortener.service;


import com.neueda.app.linkshortener.domain.shortUrl.ShortUrl;
import com.neueda.app.linkshortener.domain.shortUrl.ShortUrlRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;


@RunWith(MockitoJUnitRunner.class)
public class ShortUrlServiceTest {
    @Spy
    private ShortUrlService shortUrlService;
    @Mock
    private ShortUrlRepository shortUrlRepository;
    @Mock
    private EncoderService encoderService;

    @Before
    public void init() {
        shortUrlService.setShortUrlRepository(shortUrlRepository);
        shortUrlService.setEncoderService(encoderService);
    }

    @Test
    public void test_makeShorterOk() {
        ShortUrl toBeReturned = new ShortUrl();

        doReturn(true).when(shortUrlService).isUrlValid(anyString());
        doReturn(null).when(shortUrlRepository).findByOriginalUrl(anyString());
        doReturn("").when(encoderService).encode(anyString());
        doReturn(toBeReturned).when(shortUrlRepository).save(any(ShortUrl.class));

        Assert.assertEquals(toBeReturned, shortUrlService.makeShorter(anyString()));
    }

    @Test
    public void test_makeShorterUrlExistOk() {
        ShortUrl toBeReturned = new ShortUrl();

        doReturn(true).when(shortUrlService).isUrlValid(anyString());
        doReturn(toBeReturned).when(shortUrlRepository).findByOriginalUrl(anyString());

        Assert.assertEquals(toBeReturned, shortUrlService.makeShorter(anyString()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_makeShorterWrongUrl() {
        doReturn(false).when(shortUrlService).isUrlValid(anyString());
        shortUrlService.makeShorter(anyString());
    }

    @Test
    public void test_getOriginalUrlOk() {
        String originalUrl = "shortUrl";
        ShortUrl toBeReturned = new ShortUrl();
        toBeReturned.setOriginalUrl(originalUrl);

        doReturn(toBeReturned).when(shortUrlRepository).findByUuid(anyString());
        Assert.assertEquals(originalUrl, shortUrlService.getOriginalUrl(anyString()));
    }

    @Test(expected = NullPointerException.class)
    public void test_getOriginalUrlNull() {
        doReturn(null).when(shortUrlRepository).findByUuid(anyString());
        shortUrlService.getOriginalUrl(anyString());
    }

    @Test
    public void test_isUrlValid() {
        Assert.assertTrue(shortUrlService.isUrlValid("http://4pda.ru/"));
        Assert.assertTrue(shortUrlService.isUrlValid("https://habr.com/ru/"));
        Assert.assertFalse(shortUrlService.isUrlValid("habr.com/ru/"));
    }
}