package com.app.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.sms.dto.MetricsDTO;
import com.app.sms.service.MetricsService;

@RestController
public class MetricsController {
	@Autowired
	private MetricsService metricsService;

	@GetMapping("/metrics")
	public ResponseEntity<MetricsDTO> getMetrics() {
		return ResponseEntity.ok(metricsService.fetchMetrics());
	}

}
