package com.app.its.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.its.entity.Metrics;

public interface MetricsRepository extends JpaRepository<Metrics, Long> {

}
