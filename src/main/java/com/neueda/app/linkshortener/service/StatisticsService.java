package com.neueda.app.linkshortener.service;

import com.neueda.app.linkshortener.domain.statistics.ShortUrlStatistic;
import com.neueda.app.linkshortener.domain.statistics.ShortUrlStatisticExtendRepository;
import com.neueda.app.linkshortener.domain.statistics.ShortUrlStatisticModel;
import com.neueda.app.linkshortener.domain.statistics.ShortUrlStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticsService {
    private ShortUrlStatisticRepository shortUrlStatisticRepository;
    private ShortUrlStatisticExtendRepository extendRepository;

    @Autowired
    public void setExtendRepository(ShortUrlStatisticExtendRepository extendRepository) {
        this.extendRepository = extendRepository;
    }

    @Autowired
    public void setShortUrlStatisticRepository(ShortUrlStatisticRepository shortUrlStatisticRepository) {
        this.shortUrlStatisticRepository = shortUrlStatisticRepository;
    }

    public void logMakeShortUrl(String uuid) {
        Optional<ShortUrlStatistic> optional = shortUrlStatisticRepository.findById(uuid);
        ShortUrlStatistic shortUrlStatistic = optional.orElseGet(() -> new ShortUrlStatistic(uuid));

        shortUrlStatistic.incrementLinkCreationCount();
        shortUrlStatisticRepository.save(shortUrlStatistic);
    }

    public List<ShortUrlStatisticModel> getTopLinkRedirect(Integer count) {
        if (count == null) {
            throw new IllegalArgumentException("wrong top count: " + count);
        }

        return extendRepository.getTopLinkRedirect(count);
    }

    public List<ShortUrlStatisticModel> getTopLinkCreation(Integer count) {
        if (count == null) {
            throw new IllegalArgumentException("wrong top count: " + count);
        }

        return extendRepository.getTopLinkCreationCount(count);
    }

    public List<ShortUrlStatisticModel> getFullStatistic() {
        return extendRepository.getShortUrlStatistics();
    }

    public void logRedirect(String uuid) {
        Optional<ShortUrlStatistic> optional = shortUrlStatisticRepository.findById(uuid);
        ShortUrlStatistic shortUrlStatistic = optional.orElseGet(() -> new ShortUrlStatistic(uuid));

        shortUrlStatistic.incrementRedirectAmount();
        shortUrlStatisticRepository.save(shortUrlStatistic);
    }
}
