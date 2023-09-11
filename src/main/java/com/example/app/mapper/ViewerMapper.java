package com.example.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Viewer;

@Mapper
public interface ViewerMapper {

	Viewer selectByLoginId(String loginId) throws Exception;
	
}
