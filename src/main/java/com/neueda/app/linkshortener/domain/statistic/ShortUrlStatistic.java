package com.neueda.app.linkshortener.domain.statistic;

import com.neueda.app.linkshortener.domain.UuidEntity;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "short_url_statistic")
@SQLInsert(sql="INSERT INTO short_url_statistic(uuid, make_count) VALUES (?, ?) ON DUPLICATE KEY UPDATE make_count = make_count + 1" )
public class ShortUrlStatistic extends UuidEntity {
    @Column(name = "make_count")
    private int makeCount;

    public ShortUrlStatistic() {
    }

    public ShortUrlStatistic(String uuid) {
       super(uuid);
       this.makeCount = 1;
    }

    public int getMakeCount() {
        return makeCount;
    }

    public void incrementMakeCount() {
        this.makeCount++;
    }
}
