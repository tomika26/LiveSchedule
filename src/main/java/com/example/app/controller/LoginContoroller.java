package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.Member;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

public class LoginContoroller {



	@GetMapping
	public String login(Model model) {
		model.addAttribute("member", new Member());
		return "login";
	}

	@PostMapping
	public String login(@Valid @ModelAttribute("member") Member member,
			Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			return "login";
		}
		
		//if(!){
		//	errors.rejectValue("loginId","loginPassword");
		//	return "login";
		//}
		return "redirect:/live";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// セッションを破棄し、トップページへ遷移
		session.invalidate();
		return "redirect:/";
	}

}