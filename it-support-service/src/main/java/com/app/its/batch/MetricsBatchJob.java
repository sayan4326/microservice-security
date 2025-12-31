package com.app.its.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.its.client.MetricsServiceFeignClient;
import com.app.its.dto.MetricsDTO;
import com.app.its.entity.Metrics;
import com.app.its.repo.MetricsRepository;

@Component
public class MetricsBatchJob {

	@Autowired
	MetricsServiceFeignClient metricsClinet;

	@Autowired
	MetricsRepository metricsRepository;

	@Scheduled(fixedRate = 60000)
	public void run() {
		this.collectAndStoreMetrics();
	}

	private void collectAndStoreMetrics() {
		MetricsDTO metricsResponse = metricsClinet.getMetrics().getBody();
		Metrics metrics = new Metrics();
		metrics.setActiveSessions(metricsResponse.getActiveSessions());
		metrics.setStatus(metricsResponse.getStatus());
		metrics.setTimestamp(metricsResponse.getTimestamp());
		metrics.setCpuUsage(metricsResponse.getCpuUsage());
		metrics.setDiskIO(metricsResponse.getDiskIO());
		metrics.setMemeoryUsage(metricsResponse.getMemeoryUsage());
		metricsRepository.save(metrics);
	}

}
