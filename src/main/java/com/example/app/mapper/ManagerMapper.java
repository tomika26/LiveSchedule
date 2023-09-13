package com.example.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Manager;

@Mapper
public interface ManagerMapper {

	Manager selectByLoginId(String loginId) throws Exception;

}
