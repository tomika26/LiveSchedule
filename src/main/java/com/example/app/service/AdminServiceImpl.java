package com.example.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Manager;
import com.example.app.mapper.ManagerMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

	private final ManagerMapper mapper;

	@Override
	public boolean isCorrectIdAndPassword(String loginId, String loginPass) throws Exception {
		Manager admin = mapper.selectByLoginId(loginId);

		if (admin == null) {
			return false;
		}

		//if (!BCrypt.checkpw(loginPass, admin.getLoginPass())) {
		//	return false;
		//}
		return true;
	}

	
}
