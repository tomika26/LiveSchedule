package com.example.app.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Manager {

	private Integer id;
	@NotBlank
	private String loginId;
	@NotBlank
	private String loginPass;
	private String name;
}
