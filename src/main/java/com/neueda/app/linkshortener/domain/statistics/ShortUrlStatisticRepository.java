package com.neueda.app.linkshortener.domain.statistics;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShortUrlStatisticRepository extends JpaRepository<ShortUrlStatistic, String> {
    Page<ShortUrlStatistic> findAll(Pageable pageable);

   /* @Query("SELECT s FROM ShortUrlStatistic s LEFT JOIN FETCH s.uuid ON ShortUrl WHERE s.uuid = (:uuid)")
    List<ShortUrlStatistic> findAllByUuid(String uuid);*/
}
