package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.Live;

@Controller
public class LiveController {

	@GetMapping("/live")
	public String list(Model model) {
		model.addAttribute("schedule", new Live());
		return "liveList";
	}
	
	@GetMapping("/live/edit")
	public String edit(Model model) {
		model.addAttribute("schedule",new Live());
		return "liveEdit";
	}

	@PostMapping("/live/edit")
	public String add(
			@ModelAttribute("schedule") Live live,
			Model model) {
		return "liveEdit";
	}
}