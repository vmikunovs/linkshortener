package com.neueda.app.linkshortener.domain.shortUrl;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class ShortUrlRepository {
    private EntityManager em;

    @Autowired
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public ShortUrl findByShortUrl(String uuid) {
        JPAQuery<Void> query = new JPAQuery<>(em);
        JPAQuery<ShortUrl> where = query.select(QShortUrl.shortUrl)
                .from(QShortUrl.shortUrl)
                .where(QShortUrl.shortUrl.uuid.eq(uuid));
        return where.fetchOne();
    }

    public ShortUrl findByOriginalUrl(String originalUrl) {
        JPAQuery<Void> query = new JPAQuery<>(em);
        JPAQuery<ShortUrl> where = query.select(QShortUrl.shortUrl)
                .from(QShortUrl.shortUrl)
                .where(QShortUrl.shortUrl.originalUrl.eq(originalUrl));
        return where.fetchOne();
    }

    @Transactional
    public ShortUrl save(ShortUrl shortUrl) {
            em.persist(shortUrl);
            shortUrl = em.merge(shortUrl);
        return shortUrl;
    }

    @Transactional
    public ShortUrl update(ShortUrl shortUrl) {
        shortUrl = em.merge(shortUrl);
        return shortUrl;
    }
}
