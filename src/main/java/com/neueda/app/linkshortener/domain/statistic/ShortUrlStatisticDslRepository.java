package com.neueda.app.linkshortener.domain.statistic;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ShortUrlStatisticDslRepository {
    private EntityManager em;
    private final QShortUrlStatistic qShortUrlStatistic = QShortUrlStatistic.shortUrlStatistic;

    @Autowired
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public ShortUrlStatistic findByUrl(String uuid) {
        JPAQuery<Void> query = new JPAQuery<>(em);
        JPAQuery<ShortUrlStatistic> where = query.select(QShortUrlStatistic.shortUrlStatistic)
                .from(QShortUrlStatistic.shortUrlStatistic)
                .where(QShortUrlStatistic.shortUrlStatistic.uuid.eq(uuid));
        return where.fetchOne();
    }

    @Transactional
    public ShortUrlStatistic save(ShortUrlStatistic shortUrlStatistic) {
        shortUrlStatistic = em.merge(shortUrlStatistic);
        return shortUrlStatistic;
    }

    public List<ShortUrlStatistic> findTop(Long count) {
        JPAQuery<Void> query = new JPAQuery<>(em);

        JPAQuery<ShortUrlStatistic> where = query.select(qShortUrlStatistic)
                .from(qShortUrlStatistic)
                .orderBy(qShortUrlStatistic.makeCount.desc()).limit(count);
        return where.fetchAll().fetch();
    }
}
