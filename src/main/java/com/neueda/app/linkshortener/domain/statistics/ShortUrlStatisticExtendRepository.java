package com.neueda.app.linkshortener.domain.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ShortUrlStatisticExtendRepository {
    private EntityManager em;

    @Autowired
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<ShortUrlStatisticModel> getShortUrlStatistics() {
        return em.createQuery("SELECT NEW com.neueda.app.linkshortener.domain.statistics." +
                "ShortUrlStatisticModel(sus.uuid, su.originalUrl, sus.linkCreationCount, sus.redirectAmount) " +
                "FROM ShortUrlStatistic sus LEFT JOIN ShortUrl su ON sus.uuid = su.uuid").getResultList();
    }


    public List<ShortUrlStatisticModel> getTopLinkCreationCount(int limit) {
        return em.createQuery("SELECT NEW com.neueda.app.linkshortener.domain.statistics." +
                "ShortUrlStatisticModel(sus.uuid, su.originalUrl, sus.linkCreationCount, sus.redirectAmount) " +
                "FROM ShortUrlStatistic sus LEFT JOIN ShortUrl su ON sus.uuid = su.uuid ORDER BY sus.linkCreationCount DESC").setMaxResults(limit).getResultList();
    }

    public List<ShortUrlStatisticModel> getTopLinkRedirect(int limit) {
        return em.createQuery("SELECT NEW com.neueda.app.linkshortener.domain.statistics." +
                "ShortUrlStatisticModel(sus.uuid, su.originalUrl, sus.linkCreationCount, sus.redirectAmount) " +
                "FROM ShortUrlStatistic sus LEFT JOIN ShortUrl su ON sus.uuid = su.uuid ORDER BY sus.redirectAmount DESC").setMaxResults(limit).getResultList();
    }
}
