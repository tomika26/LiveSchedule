package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Member;

@Mapper
public interface MemberMapper {

	Member selectByLoginId(String loginId);
	List<Member> selectAll();

}
