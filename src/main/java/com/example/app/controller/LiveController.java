package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.Live;
import com.example.app.mapper.ScheduleMapper;
import com.example.app.service.LiveService;

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

	@GetMapping("/live/edit")
	public String edit(Model model) {
		model.addAttribute("live", new Live());
		return "liveEdit";
	}

	@PostMapping("/live/edit")
	public String editPost(
			@ModelAttribute("live") Live live,
			Model model) {
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
			@ModelAttribute("live") Live live
			) {
		
		Live existingLive = service.getLiveById(id);
		
		System.out.println(existingLive);
		
		existingLive.setDate(live.getDate());
		existingLive.setPlace(live.getPlace());
		existingLive.setEventName(live.getEventName());
		existingLive.setArtistId(live.getArtistId());
		// データベースに更新を反映
		service.updateLive(existingLive);

		return "redirect:/live";
	}
	
	@PostMapping("live/edit/{id}/delete")
	public String delete(
			@PathVariable("id") Integer id,
			RedirectAttributes rd) {
		service.deleteLive(id);
		rd.addFlashAttribute("statusMessage", "会員情報を削除しました");
		return "redirect:/live";
	}
	
	
}