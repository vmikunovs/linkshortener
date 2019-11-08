package com.neueda.app.linkshortener.domain.statistics;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlStatisticRepository extends JpaRepository<ShortUrlStatistic, String> {
    Page<ShortUrlStatistic> findAll(Pageable pageable);
}
