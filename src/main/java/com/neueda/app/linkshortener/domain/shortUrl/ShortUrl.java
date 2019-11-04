package com.neueda.app.linkshortener.domain.shortUrl;

import com.neueda.app.linkshortener.domain.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "short_urls")
public class ShortUrl extends IdEntity {
    @Column(unique = true, nullable = false)
    private String url;
    @Column(name = "original_url", unique = true, nullable = false)
    private String originalUrl;

    public ShortUrl() {
    }

    public ShortUrl(String shortUrl, String originalUrl) {
        this.url = shortUrl;
        this.originalUrl = originalUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }
}
