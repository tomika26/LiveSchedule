package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.domain.Live;

@Controller
public class LiveController {

	@GetMapping
	public String list(Model model) {
		model.addAttribute("schedule", new Live());
		return "liveEdit";
	}

}