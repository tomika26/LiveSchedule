package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.Member;
import com.example.app.mapper.MemberMapper;
import com.example.app.service.LoginService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberMapper mapper;
	private final LoginService service;

	@GetMapping("/member")
	public String member(Model model) {
		model.addAttribute("member", new Member());
		model.addAttribute("members",mapper.selectAll()); 
		//model.addAttribute("name",service.getAllMembers());
		return "member";
	}
	
	@PostMapping("/member")
	public String postMember(@ModelAttribute("loginId") String loginId,
			Model model,HttpSession session) {
		Object loginId = session.getAttribute(loginId);
		if(!"taro".equals(loginId)) {
			
			System.out.println(loginId);
			return "redirect:/live";
		}
		return "member";
	}

}
