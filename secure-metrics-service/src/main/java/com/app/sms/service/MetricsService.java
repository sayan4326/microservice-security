package com.app.sms.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.app.sms.dto.MetricsDTO;
@Service
public class MetricsService {
	private final Random random = new Random();
	
	public MetricsDTO fetchMetrics() {
		MetricsDTO metric = new MetricsDTO();
		metric.setTimestamp(LocalDateTime.now());
		metric.setCpuUsage(random.nextInt(100) + "%");
		metric.setMemeoryUsage(random.nextInt(16000) + "MB");
		metric.setDiskIO(random.nextInt(500) + "MB/s");
		metric.setActiveSessions(random.nextInt(200));
		metric.setStatus("UP");
		return metric;
	}

}
