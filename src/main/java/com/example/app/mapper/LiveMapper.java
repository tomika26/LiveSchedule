package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.app.domain.Live;



@Mapper
public interface LiveMapper {

	List<Live> selectAll() throws Exception;

	Live selectById(Integer id) throws Exception;

	// ページ分割機能用
	Long count() throws Exception;

	List<Live> selectLimited(@Param("offset") int offset,
			@Param("limit") int limit) throws Exception;

	void insert(Live live) throws Exception;

	void update(Live live) throws Exception;

	void delete(Integer id) throws Exception;
}
