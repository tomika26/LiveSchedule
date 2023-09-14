package com.example.app.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.app.domain.Member;
import com.example.app.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
//@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final MemberMapper mapper;

	@Override
	public boolean isCorrectIdAndPassword(String loginId, String loginPass) throws Exception {
		Member login = mapper.selectByLoginId(loginId);

		if (login == null) {
			return false;
		}

		if (!BCrypt.checkpw(loginPass, login.getLoginPass())) {
			return false;
		}
		return true;
	}

}