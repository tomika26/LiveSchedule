package com.example.app.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Member {

	private Integer id;
	@NotBlank(message = "※必須項目です")
	private String loginId;
	@NotBlank(message = "※必須項目です")
	private String loginPass;
	private String name;
}
