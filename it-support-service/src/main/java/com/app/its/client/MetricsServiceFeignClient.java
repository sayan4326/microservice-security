package com.app.its.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.its.dto.MetricsDTO;


@FeignClient(name="metrics-client-service", url="http://localhost:37023/Omet")
public interface MetricsServiceFeignClient {
	@GetMapping("/metrics")
	public ResponseEntity<MetricsDTO> getMetrics();

}
