package com.neueda.app.linkshortener.domain.statistics;

import com.neueda.app.linkshortener.domain.shortUrl.ShortUrl;

import javax.persistence.*;

//@Entity
//@SqlResultSetMappings({
//        @SqlResultSetMapping(
//                name = "ShortUrlStatisticMapping",
//                classes = @ConstructorResult(
//                        targetClass = ShortUrlStatistic.class,
//                        columns = {
//                                @ColumnResult(name = "uuid"),
//                                @ColumnResult(name = "linkCreationCount", type = long.class),
//                                @ColumnResult(name = "redirectAmount", type = long.class)
//                        }
//                )
//        ),
//        @SqlResultSetMapping(
//                name = "ShortUrlMapping",
//                classes = @ConstructorResult(
//                        targetClass = ShortUrl.class,
//                        columns = {
//                                @ColumnResult(name = "uuid"),
//                                @ColumnResult(name = "originalUrl", type = String.class),
//                        }
//                )
//        )
//})
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
