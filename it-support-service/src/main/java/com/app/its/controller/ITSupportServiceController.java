package com.app.its.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.its.dto.MetricsDTO;
import com.app.its.entity.Metrics;
import com.app.its.repo.MetricsRepository;

@RestController
public class ITSupportServiceController {

	@Autowired
	MetricsRepository metricsRepository;

	@PostMapping("/support/ticket")
	public ResponseEntity<Object> createTicket() {
		String output = "TICKET ID-123 created";
		return new ResponseEntity<>(output, HttpStatus.CREATED);
	}

	@PutMapping("/support/ticket/{ticketId}/reopen")
	public ResponseEntity<Object> reOpenTicket(@PathVariable Long ticketId) {
		String output = "TICKET ID-123 reopend by" + "User:" + "Role:";
		return new ResponseEntity<>(output, HttpStatus.OK);
	}

	@PutMapping("/support/ticket/{ticketId}/close")
	public ResponseEntity<Object> closeTicket(@PathVariable Long ticketId) {
		String output = "TICKET ID-123 closed by" + "User:" + "Role:";
		return new ResponseEntity<>(output, HttpStatus.OK);
	}

	@GetMapping("/support/metrics")
	public ResponseEntity<List<MetricsDTO>> getMetrics() {
		List<MetricsDTO> metricsDTOList = new ArrayList<MetricsDTO>();

		List<Metrics> allMetrics = metricsRepository.findAll();
		for (Metrics x : allMetrics) {
			metricsDTOList.add(MetricsDTO.builder().activeSessions(x.getActiveSessions()).cpuUsage(x.getCpuUsage())
					.status(x.getStatus()).diskIO(x.getDiskIO()).timestamp(x.getTimestamp())
					.memeoryUsage(x.getMemeoryUsage()).build());
		}
		return ResponseEntity.ok(metricsDTOList);
	}

}
