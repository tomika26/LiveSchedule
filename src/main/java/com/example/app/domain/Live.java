package com.example.app.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class Live {
	private Integer id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private String place;
	@NotBlank
	private String eventName;
	private Integer artistId;
	private String name;
	@Size(min = 0, max = 300, message = "感想は{max}文字までです")
	private String impression;
	private String searchDate;
}
