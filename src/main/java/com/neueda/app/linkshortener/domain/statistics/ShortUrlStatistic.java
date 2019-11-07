package com.neueda.app.linkshortener.domain.statistics;

import com.neueda.app.linkshortener.domain.UuidEntity;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;

@Entity
@Table(name = "short_url_statistics")
@SQLInsert(sql = "INSERT INTO short_url_statistics(link_creation_count, redirect_amount, uuid) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE link_creation_count = link_creation_count + 1")
public class ShortUrlStatistic extends UuidEntity {
    @Column(name = "link_creation_count")
    private long linkCreationCount;
    @Column(name = "redirect_amount")
    private long redirectAmount;

    public ShortUrlStatistic() {
    }

    public ShortUrlStatistic(String uuid) {
        super(uuid);
        this.redirectAmount = 0;
    }

    public long getLinkCreationCount() {
        return linkCreationCount;
    }

    public void incrementLinkCreationCount() {
        this.linkCreationCount++;
    }

    public long getRedirectAmount() {
        return redirectAmount;
    }

    public void incrementRedirectAmount() {
        this.redirectAmount++;
    }
}
