package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.Member;
import com.example.app.service.LoginService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

public class LoginContoroller {

	@Autowired
	public LoginService service;

	@GetMapping
	public String login(Model model) {
		model.addAttribute("member", new Member());
		return "login";
	}

	@PostMapping
	public String login(@Valid @ModelAttribute("member") Member member,
			Errors errors,
			Model model,
			HttpSession session) throws Exception {
		if (errors.hasErrors()) {
			return "login";
		}

		Member loginMember =
		    service.isCorrectIdAndPassword(member.getLoginId(), member.getLoginPass());
		
		if (loginMember == null) {
			errors.rejectValue("loginId", "error.incorrect_id_password","IDまたはパスワードが正しくありません");
			return "login";
		}
		session.setAttribute("member", loginMember);
		return "redirect:/live";
	}

	@GetMapping("/logout")
	public String showLogout(Model model) {
		
		
		return "logout";
	}
	
	@GetMapping("/login")
	public String logout(HttpSession session) {
		
		// セッションを破棄し、ログアウトへ遷移
		session.invalidate();
		return "redirect:/";
	}

}