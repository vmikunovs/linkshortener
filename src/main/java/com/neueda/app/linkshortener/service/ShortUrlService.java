package com.neueda.app.linkshortener.service;

import com.neueda.app.linkshortener.domain.shortUrl.ShortUrl;
import com.neueda.app.linkshortener.domain.shortUrl.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlService {
    public static final String URL_PATTERN = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    private ShortUrlRepository shortUrlRepository;
    private EncoderService encoderService;

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

        shortUrl = shortUrlRepository.save(new ShortUrl(encode, originalUrl));
        return shortUrl;
    }

    protected boolean isUrlValid(String url) {
        return url.matches(URL_PATTERN);
    }

    public String getOriginalUrl(String url) {
        ShortUrl shortUrl = shortUrlRepository.findByShortUrl(url);
        if (shortUrl == null) {
            throw new NullPointerException("short url: ");
        }

        return shortUrl.getOriginalUrl();
    }
}
