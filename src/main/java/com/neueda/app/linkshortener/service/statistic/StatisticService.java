package com.neueda.app.linkshortener.service.statistic;

import com.neueda.app.linkshortener.domain.statistic.ShortUrlStatistic;
import com.neueda.app.linkshortener.domain.statistic.ShortUrlStatisticDslRepository;
import com.neueda.app.linkshortener.domain.statistic.ShortUrlStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {
    private ShortUrlStatisticDslRepository shortUrlStatisticDslRepository;
    private ShortUrlStatisticRepository shortUrlStatisticRepository;

    @Autowired
    public void setShortUrlStatisticDslRepository(ShortUrlStatisticDslRepository shortUrlStatisticDslRepository) {
        this.shortUrlStatisticDslRepository = shortUrlStatisticDslRepository;
    }

    public StatisticModel getStatistic() {
        return new StatisticModel();
    }

    public void logMakeShortUrl(String url) {
        ShortUrlStatistic shortUrlStatistic = shortUrlStatisticDslRepository.findByUrl(url);
        if (shortUrlStatistic == null) {
            shortUrlStatistic = new ShortUrlStatistic(url);
        }

        //ToDo insert on duplicate key and update
        shortUrlStatistic.incrementMakeCount();
        shortUrlStatisticDslRepository.save(shortUrlStatistic);
    }

    public List<ShortUrlStatistic> getTop(Long count) {
        if (count == null) {
            throw new IllegalArgumentException("wrong top count: " + count);
        }

        return shortUrlStatisticDslRepository.findTop(count);
    }
}
