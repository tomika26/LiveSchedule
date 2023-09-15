package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Live;

@Mapper
public interface ScheduleMapper {

	List<Live> selectAll();

	Live selectById(Integer id);

	
	void insert(Live live);

	
	void update(Live live);

	
	void delete(Integer id);
}
