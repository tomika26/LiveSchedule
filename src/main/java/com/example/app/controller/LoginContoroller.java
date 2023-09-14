package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.Member;
import com.example.app.service.LoginService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("login")
public class LoginContoroller {

	private final LoginService service;

	@GetMapping
	public String login(Model model) {
		model.addAttribute("member", new Member());
		return "liveList";
	}

	@PostMapping
	public String login(@Valid @ModelAttribute("member") Member member,
			Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			return "login";
		}
		return "liveList";
	}

}