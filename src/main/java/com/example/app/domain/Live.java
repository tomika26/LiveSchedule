package com.example.app.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Live {

	
	private Integer id;
	private LocalDate date;
	private String place;
	private String iventName;
	private Artist artist;
}
