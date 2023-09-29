package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String Member(
			Model model, HttpSession session,
			RedirectAttributes rd) {
		model.addAttribute("member", new Member());
		model.addAttribute("members", mapper.selectAll());
		
String managerId = (String) session.getAttribute("loginId");
		Member member = service.getMemberById("taro");
		
		System.out.println(managerId);
		if (managerId == null || !managerId.equals(member.getLoginId())) {
			rd.addFlashAttribute("unauthorized", "権限はありません");
			return "redirect:/live";
		}
		return "member";
	}

}
