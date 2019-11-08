package com.neueda.app.linkshortener.service;

import com.neueda.app.linkshortener.domain.shortUrl.ShortUrl;
import com.neueda.app.linkshortener.domain.shortUrl.ShortUrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class ShortUrlService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private EncoderService encoderService;
    private ShortUrlRepository shortUrlRepository;

    @Autowired
    public void setEncoderService(EncoderService encoderService) {
        this.encoderService = encoderService;
    }

    @Autowired
    public void setShortUrlRepository(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public ShortUrl makeShorter(String originalUrl) {
        originalUrl = originalUrl.trim();
        if (!isUrlValid(originalUrl)) {
            throw new IllegalArgumentException(" wrong url: " + originalUrl);
        }

        ShortUrl shortUrl = shortUrlRepository.findByOriginalUrl(originalUrl);

        if (shortUrl != null) {
            return shortUrl;
        }

        String encode = encoderService.encode(originalUrl);

        shortUrl = new ShortUrl(encode, originalUrl);
        shortUrl = shortUrlRepository.save(shortUrl);
        log.info("make new short url from: " + shortUrl.getOriginalUrl() + " uuid: " + shortUrl.getUuid());
        return shortUrl;
    }

    boolean isUrlValid(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getOriginalUrl(String uuid) {
        ShortUrl shortUrl = shortUrlRepository.findByUuid(uuid);
        if (shortUrl == null) {
            throw new NullPointerException("short url: ");
        }

        return shortUrl.getOriginalUrl();
    }
}
