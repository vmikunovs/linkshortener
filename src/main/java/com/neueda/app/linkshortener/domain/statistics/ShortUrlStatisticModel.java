package com.neueda.app.linkshortener.domain.statistics;

public class ShortUrlStatisticModel {
    private String uuid;
    private String originalUrl;
    private long linkCreationCount;
    private long redirectAmount;

    public ShortUrlStatisticModel() {
    }

    public ShortUrlStatisticModel(String uuid, String originalUrl, long linkCreationCount, long redirectAmount) {
        this.uuid = uuid;
        this.originalUrl = originalUrl;
        this.linkCreationCount = linkCreationCount;
        this.redirectAmount = redirectAmount;
    }

    public String getUuid() {
        return uuid;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public long getLinkCreationCount() {
        return linkCreationCount;
    }

    public long getRedirectAmount() {
        return redirectAmount;
    }
}
