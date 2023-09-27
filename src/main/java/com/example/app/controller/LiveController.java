package com.example.app.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.Live;
import com.example.app.mapper.ScheduleMapper;
import com.example.app.service.LiveService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LiveController {

	private final ScheduleMapper mapper;
	private final LiveService service;

	@GetMapping("/live")
	public String list(Model model) {
		model.addAttribute("schedules", mapper.selectAll());

		return "liveList";
	}

	@PostMapping("/live")
	public String search(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
			Model model) {
		List<Live> liveDate = service.getLiveByDate(date);
		System.out.println(liveDate);
		if (liveDate.size() == 0) {
			System.out.println("情報はありません。");
			model.addAttribute("notDate");
			return "redirect:/live";
		}

		model.addAttribute("schedules", liveDate);
		return "liveList";
	}

	

	@GetMapping("/live/edit")
	public String edit(Model model) {
		model.addAttribute("live", new Live());
		return "liveEdit";
	}

	@PostMapping("/live/edit")
	public String editPost(@Valid @ModelAttribute("live") Live live,
			Errors errors) {
		if (errors.hasErrors()) {

			return "liveEdit";
		}
		mapper.insert(live);
		return "redirect:/live";
	}

	//---------------------

	@GetMapping("live/edit/{id}")
	public String update(
			@PathVariable("id") Integer id, Model model) {
		Live live = service.getLiveById(id);
		System.out.println(live);
		if (live != null) {
			model.addAttribute("live", live);
			return "liveEdit";
		} else {
			return "redirect:/live";
		}
	}

	@PostMapping("/live/edit/{id}")
	public String updatePost(
			@PathVariable("id") Integer id,
			@Valid @ModelAttribute("live") Live live,
			Errors errors) {
		if (errors.hasErrors()) {
			return "liveEdit";
		}

		// データベースに更新を反映
		live.setId(id);
		service.updateLive(live);

		return "redirect:/live";
	}

	@GetMapping("live/edit/{id}/delete")
	public String delete(
			@PathVariable("id") Integer id,
			Model model,
			RedirectAttributes rd) {
		Live live = service.getLiveById(id);
		System.out.println(live);
		service.deleteLive(id);
		rd.addFlashAttribute("statusMessage", "イベントを削除しました");
		return "redirect:/live";
	}

}