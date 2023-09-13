package com.example.app.service;

import org.springframework.stereotype.Service;

import com.example.app.domain.Live;

import jakarta.validation.Valid;

@Service
public interface LiveService {

	Object getScheduleListByPage(Integer page, int numPerPage);

	Object getTotalPages(int numPerPage);

	Object getTypeList();

	void editMember(@Valid Live live);

	void deleteMember(Integer id);

	

	
}
