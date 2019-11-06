package com.neueda.app.linkshortener.domain.statistic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlStatisticRepository extends JpaRepository<ShortUrlStatistic, String> {

}
