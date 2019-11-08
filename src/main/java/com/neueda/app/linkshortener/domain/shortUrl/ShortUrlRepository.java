package com.neueda.app.linkshortener.domain.shortUrl;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ShortUrlRepository {
    private EntityManager em;
    private QShortUrl qShortUrl = QShortUrl.shortUrl;

    @Autowired
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public ShortUrl findByUuid(String uuid) {
        JPAQuery<Void> query = new JPAQuery<>(em);
        JPAQuery<ShortUrl> where = query.select(qShortUrl)
                .from(qShortUrl)
                .where(qShortUrl.uuid.eq(uuid));
        return where.fetchOne();
    }

    public ShortUrl findByOriginalUrl(String originalUrl) {
        JPAQuery<Void> query = new JPAQuery<>(em);
        JPAQuery<ShortUrl> where = query.select(qShortUrl)
                .from(qShortUrl)
                .where(qShortUrl.originalUrl.eq(originalUrl));
        return where.fetchOne();
    }

    @Transactional
    public ShortUrl save(ShortUrl shortUrl) {
            em.persist(shortUrl);
            shortUrl = em.merge(shortUrl);
        return shortUrl;
    }

    public List<ShortUrl> findAll() {
        JPAQuery<Void> query = new JPAQuery<>(em);
        JPAQuery<ShortUrl> where = query.select(qShortUrl)
                .from(qShortUrl);
        return where.fetch();
    }
}
