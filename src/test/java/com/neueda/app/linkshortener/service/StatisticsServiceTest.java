package com.neueda.app.linkshortener.service;

import com.neueda.app.linkshortener.domain.statistics.ShortUrlStatistic;
import com.neueda.app.linkshortener.domain.statistics.ShortUrlStatisticExtendRepository;
import com.neueda.app.linkshortener.domain.statistics.ShortUrlStatisticRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceTest {
    @Spy
    private StatisticsService statisticsService;
    @Mock
    private ShortUrlStatisticRepository shortUrlRepository;
    @Mock
    private ShortUrlStatisticExtendRepository extendRepository;

    @Before
    public void init() {
        statisticsService.setShortUrlStatisticRepository(shortUrlRepository);
        statisticsService.setExtendRepository(extendRepository);
    }

    @Test
    public void test_logMakeShortUrlOk() {
        ShortUrlStatistic urlStatistic = new ShortUrlStatistic();
        urlStatistic.setLinkCreationCount(1);
        Optional<ShortUrlStatistic> optional = Optional.of(urlStatistic);

        doReturn(optional).when(shortUrlRepository).findById(anyString());
        statisticsService.logMakeShortUrl(anyString());
        assertEquals(2, optional.get().getLinkCreationCount());
        verify(shortUrlRepository, times(1)).save(any(ShortUrlStatistic.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getTopLinkRedirectNullCount() {
        statisticsService.getTopLinkRedirect(null);
    }

    @Test
    public void test_getTopLinkRedirectOk() {
       statisticsService.getTopLinkRedirect(anyInt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getTopLinkCreationNullCount() {
        statisticsService.getTopLinkCreation(null);
    }
    public void test_getTopLinkCreationOk() {
        statisticsService.getTopLinkCreation(anyInt());
    }

    @Test
    public void test_logRedirect() {
        ShortUrlStatistic urlStatistic = new ShortUrlStatistic();
        urlStatistic.setRedirectAmount(1);
        Optional<ShortUrlStatistic> optional = Optional.of(urlStatistic);

        doReturn(optional).when(shortUrlRepository).findById(anyString());
        statisticsService.logRedirect(anyString());
        assertEquals(2, optional.get().getRedirectAmount());
        verify(shortUrlRepository, times(1)).save(any(ShortUrlStatistic.class));
    }
}