package com.app.its.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MetricsDTO {

	private String status;
	private LocalDateTime timestamp;
	private String cpuUsage;
	private String memeoryUsage;
	private String diskIO;
	private int activeSessions;
	
}
