package com.neueda.app.linkshortener.service;

import com.neueda.app.linkshortener.domain.statistic.ShortUrlStatistic;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {
    public ShortUrlStatistic getStatistic() {
        return new ShortUrlStatistic();
    }

}
