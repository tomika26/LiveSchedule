package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.domain.Live;
import com.example.app.mapper.ScheduleMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LiveServiceImpl implements LiveService {
	private final ScheduleMapper mapper;

	@Override
	public List<Live> getAllLives() {

		return mapper.selectAll();
	}

	@Override
	public Live getLiveById(int id) {

		return mapper.selectById(id);
	}

	@Override
	public void addLive(Live live) {
		mapper.insert(live);

	}

	@Override
	public void updateLive(Live live) {
		mapper.update(live);

	}

	@Override
	public void deleteLive(int id) {

		mapper.delete(id);

	}

}
