package com.example.app.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data

public class Live {
	private Integer id;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate date;
	private String place;
	private String eventName;
	private Integer artistId;
	private String name;
}
