package com.neueda.app.linkshortener.domain.shortUrl;

import ch.vorburger.exec.ManagedProcessException;
import org.junit.*;
import utils.MariaDbLauncher;

import javax.persistence.EntityTransaction;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ShortUrlRepositoryTest {
    private MariaDbLauncher mariaDbLauncher = new MariaDbLauncher();
    private ShortUrlRepository shortUrlRepository = new ShortUrlRepository();

    @Before
    public void before() {
        mariaDbLauncher.launch();
        shortUrlRepository.setEm(mariaDbLauncher.getMariaDb().createOrGetEntityManager());
    }

    @After
    public void after() throws ManagedProcessException {
        mariaDbLauncher.closeDB();
    }

    @Test
    public void test_findByUuid() {
        ShortUrl shortUrl1 = new ShortUrl("dHsN2sJFRSqkGNvPX5cl9Q", "https://habr.com/ru/");
        ShortUrl shortUrl2 = new ShortUrl("3NK-q9dwRxmW6mmViJPonw", "https://habr.com/");
        ShortUrl shortUrl3 = new ShortUrl("QhcDcfLxR02kaCxnxl63EQ", "https://habr.com/test");

        mariaDbLauncher.persist(shortUrl1, shortUrl2, shortUrl3);

        ShortUrl shortUrl = shortUrlRepository.findByUuid(shortUrl3.getUuid());
        assertEquals(shortUrl3, shortUrl);
        assertNotEquals(shortUrl1, shortUrl);
        assertNotEquals(shortUrl2, shortUrl);
    }

    @Test
    public void test_findByOriginalUrl() {
        ShortUrl shortUrl4 = new ShortUrl("PJqZVG6US-uwTfusPiLYRw", "https://habr.com/test1");
        ShortUrl shortUrl5 = new ShortUrl("uNFx_1ZHT4ubIIlKibhhcw", "https://habr.com/test2");
        ShortUrl shortUrl6 = new ShortUrl("EPIFocVLQ12LgpzqUOh_Fg", "https://habr.com/test3");

        mariaDbLauncher.persist(shortUrl4, shortUrl5, shortUrl6);

        ShortUrl shortUrl = shortUrlRepository.findByOriginalUrl(shortUrl6.getOriginalUrl());
        assertEquals(shortUrl6, shortUrl);
        assertNotEquals(shortUrl4, shortUrl);
        assertNotEquals(shortUrl5, shortUrl);
    }

    @Test
    public void test_save() {
        ShortUrl shortUrl1 = new ShortUrl("dHsN2sJFRSqkGNvPX5cl9Q", "https://habr.com/ru/");
        ShortUrl shortUrl2 = new ShortUrl("3NK-q9dwRxmW6mmViJPonw", "https://habr.com/");
        ShortUrl shortUrl3 = new ShortUrl("QhcDcfLxR02kaCxnxl63EQ", "https://habr.com/test");
        ShortUrl shortUrl4 = new ShortUrl("PJqZVG6US-uwTfusPiLYRw", "https://habr.com/test1");

        List<ShortUrl> list = shortUrlRepository.findAll();
        assertEquals(0, list.size());

        EntityTransaction transaction = mariaDbLauncher.getMariaDb().createOrGetEntityManager().getTransaction();
        transaction.begin();

        shortUrlRepository.save(shortUrl1);
        shortUrlRepository.save(shortUrl2);
        shortUrlRepository.save(shortUrl3);
        shortUrlRepository.save(shortUrl4);

        transaction.commit();

        list = shortUrlRepository.findAll();
        assertEquals(4, list.size());
    }
}