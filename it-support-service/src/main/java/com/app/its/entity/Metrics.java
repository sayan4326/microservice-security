package com.app.its.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Metrics {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long metricsId;
	private String status;
	private LocalDateTime timestamp;
	private String cpuUsage;
	private String memeoryUsage;
	private String diskIO;
	private int activeSessions;
}
