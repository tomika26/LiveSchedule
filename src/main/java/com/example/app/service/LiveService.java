package com.example.app.service;

import java.util.List;

import com.example.app.domain.Live;

public interface LiveService {

	List<Live> getschedulesList();

	void addLive(Live live) throws Exception;

	void editLive(Live member) throws Exception;

	void deleteLive(Integer id) throws Exception;

}
