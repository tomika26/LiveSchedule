package com.example.app.service;

public interface LoginService {

	boolean isCorrectIdAndPassword(String loginId, String loginPass)
			throws Exception;
}
