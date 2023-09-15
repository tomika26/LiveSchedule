package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.domain.Member;
import com.example.app.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberMapper mapper;

	@GetMapping("/member")
	public String member(Model model) {
		model.addAttribute("member", new Member());
		model.addAttribute("members",mapper.selectAll()); 
		return "member";
	}

}
