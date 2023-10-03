package com.example.app.service;

import java.util.List;

import com.example.app.domain.Member;
import com.example.app.mapper.MemberMapper;

public interface LoginService {
	
	 private final MemberMapper memberMapper;
	
	 public void registerMember(Member member) {
	        // パスワードをハッシュ化
	        String hashedPassword = PasswordUtils.hashPassword(member.getLoginPass());
	        member.setLoginPass(hashedPassword);

	        // データベースに保存
	        memberMapper.insert(member);
	    }
	// 正しいIDとパスワードの場合、会員情報を返す
	Member isCorrectIdAndPassword(String loginId, String loginPass)
			throws Exception;

	List<Member> getAllMembers(); // すべてのMember情報を取得

	Member getMemberById(String loginId);
	
	

}
