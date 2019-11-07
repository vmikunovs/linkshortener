package com.neueda.app.linkshortener.domain.shortUrl;

import com.neueda.app.linkshortener.domain.UuidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "short_urls")
public class ShortUrl extends UuidEntity {
    @Column(name = "original_url", nullable = false)
    private String originalUrl;

    public ShortUrl() {
    }

    public ShortUrl(String uuid, String originalUrl) {
        super(uuid);
        this.originalUrl = originalUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
