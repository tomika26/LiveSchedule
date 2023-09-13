package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.Manager;
import com.example.app.service.AdminService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
//@RequiredArgsConstructor
public class AdminLogin {

	private final AdminService service = null;

	@GetMapping
	public String login(Model model) {
		model.addAttribute("admin", new Manager());
		return "login";
	}

	//@GetMapping
	//public String login() {
	//	return "/login";
	//}
	//ーザーが入力したIDとパスワードの確認を行う。
	//問題がなければ、管理者データをセッションに格納し、
	//admin/resultへリダイレクトする

	@PostMapping
	public String login(
			@Valid Manager admin,
			Errors errors,
			HttpSession session) throws Exception {
		// 入力に不備がある
		if (errors.hasErrors()) {
			return "/";
		}
		// パスワードが正しくない
		if (!service.isCorrectIdAndPassword(admin.getLoginId(),
				admin.getLoginPass())) {
			errors.rejectValue("loginId", "error.incorrect_id_password");
			return "/";
		}
		// 正しいログインID とパスワード
		// ⇒ セッションにログインID を格納し、リダイレクト
		session.setAttribute("loginId", admin.getLoginId());
		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// セッションを破棄し、トップページへ遷移
		session.invalidate();
		return "redirect:/";
	}

}