package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.Live;
import com.example.app.service.LiveService;

import jakarta.validation.Valid;

@Controller
public class ManagerResult {

	private static final int NUM_PER_PAGE = 10;

	private final LiveService service;

	@GetMapping
	public String home() {
		return "/";
	}

	@GetMapping
	public String list(
			@RequestParam(name = "page", defaultValue = "1") Integer page,
			Model model) throws Exception {
		model.addAttribute("schedules",service.getScheduleListByPage(page, NUM_PER_PAGE));
		model.addAttribute("page", page);
		model.addAttribute("totalPages",service.getTotalPages(NUM_PER_PAGE));
		return "members/list";
	}

	@GetMapping
	public String addGet(Model model) throws Exception {
		model.addAttribute("title", "会員の追加");
		model.addAttribute("schedule", new Live());
		model.addAttribute("types", service.getTypeList());
		return "/";
	}

	@PostMapping("/add")
	public String addPost(
			@Valid Live schedule,
			Errors errors,
			RedirectAttributes rd,
			Model model) throws Exception {
		if (errors.hasErrors()) {
			model.addAttribute("title", "会員の追加");
			//model.addAttribute("types", service.getTypeList());
			return "members/save";

		}

		//service.addMember(member);
		rd.addFlashAttribute("statusMessage", "会員を追加しました。");
		return "redirect:/members";

	}

	@GetMapping("/edit/{id}")
	public String editGet(@PathVariable Integer id, Model model) throws Exception {
		model.addAttribute("title", "会員情報の変更");
		//model.addAttribute("schedule", service.getMemberById(id));
		//model.addAttribute("types", service.getTypeList());
		return "members/save";
	}

	@PostMapping("/edit/{id}")
	public String editPost(
			@PathVariable Integer id,
			@Valid Live live,
			Errors errors,
			RedirectAttributes rd,
			Model model) throws Exception {
		if (errors.hasErrors()) {
			model.addAttribute("title", "会員情報の変更");
			model.addAttribute("types", service.getTypeList());
			return "members/save";
		}
		live.setId(id); //更新に必要な会員ID をセット
		service.editMember(live);
		rd.addFlashAttribute("statusMessage", "会員情報を更新しました。");
		return "redirect:/members";
	}

	@GetMapping("/delete/{id}")
	public String dalete(@PathVariable Integer id, RedirectAttributes rd)
			throws Exception {
		service.deleteMember(id);
		rd.addFlashAttribute("statusMessage", "会員情報を削除しました");
		return "redirect:/members";
	}

}
