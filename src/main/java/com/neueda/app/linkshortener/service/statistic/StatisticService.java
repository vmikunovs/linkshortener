package com.neueda.app.linkshortener.service.statistic;

import com.neueda.app.linkshortener.domain.statistics.ShortUrlStatistic;
import com.neueda.app.linkshortener.domain.statistics.ShortUrlStatisticExtendRepository;
import com.neueda.app.linkshortener.domain.statistics.ShortUrlStatisticModel;
import com.neueda.app.linkshortener.domain.statistics.ShortUrlStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticService {
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

    public StatisticModel getStatistic() {
        return new StatisticModel();
    }

    public void logMakeShortUrl(String uuid) {
        Optional<ShortUrlStatistic> optional = shortUrlStatisticRepository.findById(uuid);
        ShortUrlStatistic shortUrlStatistic = optional.orElseGet(() -> new ShortUrlStatistic(uuid));

        shortUrlStatistic.incrementLinkCreationCount();
        shortUrlStatisticRepository.save(shortUrlStatistic);
    }

    public List<ShortUrlStatistic> getTop(Integer count) {
        if (count == null) {
            throw new IllegalArgumentException("wrong top count: " + count);
        }

        Page<ShortUrlStatistic> urlStatisticPage = shortUrlStatisticRepository.findAll(PageRequest.of(0, count, Sort.by("makeCount").descending()));
        return urlStatisticPage.getContent();
    }

    public List<ShortUrlStatistic> getFullStatistic() {
        List<ShortUrlStatisticModel> shortUrlStatistics = extendRepository.getShortUrlStatistics();
        return shortUrlStatisticRepository.findAll();
    }
}
