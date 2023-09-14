package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Live;



@Mapper
public interface ScheduleMapper {

	List<Live> selectAll() throws Exception;

	Live selectById(Integer id) throws Exception;


	
	void insert(Live live) throws Exception;

	void update(Live live) throws Exception;

	void delete(Integer id) throws Exception;
}
