package com.example.app.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.Member;
import com.example.app.mapper.MemberMapper;
import com.example.app.service.LoginService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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

	@PostMapping("/member")
	public String editPost(Model model, @Valid @ModelAttribute("member") Member member,
			Errors errors) {
		// エラーがある場合はフォームを再表示
		// その際に表示させる会員リストの情報が必要
		if (errors.hasErrors()) {
			model.addAttribute("members", mapper.selectAll());
			return "member";
		}
		
		// 不備がなければ、パスワードをハッシュ化して、登録
		String hashed = BCrypt.hashpw(member.getLoginPass(), BCrypt.gensalt());
		member.setLoginPass(hashed);
		mapper.insert(member);

		return "redirect:/member";
	}
	
	@GetMapping("/member/edit")
	public String edit(Model model) {
		model.addAttribute("member", new Member());
		return "member";
	}

	

	@PostMapping("/member/delete")
	public String deleteMember(@RequestParam(required = false) Integer memberNo,
			Model model, RedirectAttributes rd) {

		if (memberNo == null) {
			rd.addAttribute("notDeleteMessage", "NO.を入れてください");
			return "redirect:/member";
		}

		mapper.delete(memberNo);
		rd.addFlashAttribute("deleteMessage", "削除しました");
		return "redirect:/member";
	}


}
