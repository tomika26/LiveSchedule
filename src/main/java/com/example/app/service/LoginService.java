package com.example.app.service;

import java.util.List;

import com.example.app.domain.Member;

public interface LoginService {

	// 正しいIDとパスワードの場合、会員情報を返す
	Member isCorrectIdAndPassword(String loginId, String loginPass)
			throws Exception;

	List<Member> getAllMembers(); // すべてのMember情報を取得
}
