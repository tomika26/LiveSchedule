package com.example.app.service;

import java.util.List;

import com.example.app.domain.Live;
import com.example.app.domain.Member;

public interface LiveService {

	List<Live> getschedulesList();

	void addMember(Member member) throws Exception;

	void editMember(Member member) throws Exception;

	void deleteMember(Integer id) throws Exception;

}
