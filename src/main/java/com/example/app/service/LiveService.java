package com.example.app.service;

import java.time.LocalDate;
import java.util.List;

import com.example.app.domain.Live;

public interface LiveService {
	List<Live> getAllLives(); // すべてのLive情報を取得

	Live getLiveById(int id); // 指定したIDのLive情報を取得

	

	void addLive(Live live); // Live情報を追加

	void updateLive(Live live); // Live情報を更新

	void deleteLive(int id); // 指定したIDのLive情報を削除

	List<Live> getLiveByDate(LocalDate date);

	
}