package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.Live;
import com.example.app.mapper.ScheduleMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LiveController {
	
	private final ScheduleMapper mapper;

	@GetMapping("/live")
	public String list(Model model){
		model.addAttribute("schedules", mapper.selectAll());
		return "liveList";
	}
	
	@GetMapping("/live/edit")
	public String edit(Model model) {
		model.addAttribute("live",new Live());
		return "liveEdit";
	}

	

	@PostMapping("/live/edit")
	public String add(
			@ModelAttribute("live") Live live,
			Model model) {
		mapper.insert(live);
		return "redirect:/live";
	}
	
	
}