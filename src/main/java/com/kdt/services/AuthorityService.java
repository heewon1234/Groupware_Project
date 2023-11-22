package com.kdt.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.AuthorityDAO;

@Service
public class AuthorityService {
	
	@Autowired
	AuthorityDAO adao;
	
	public boolean isExistAuth(String id, String board_title) {
		Map<String,String> map = new HashMap<>();
		map.put("id", id);
		map.put("board_title", board_title);
		return adao.isExistAuth(map);
	}
	public boolean isWriteAuthExist(String id) {
		return adao.isWriteAuthExist(id);
	}
	public boolean isAuthBoardExist(String id) {
		return adao.isAuthBoardExist(id);
	}
	
	public List<String> selectAuthBoard(String id){
		return adao.selectAuthBoard(id);
	}
	
	public List<Map<String,String>> selectAuthMember(String board_title){
		return adao.selectAuthMember(board_title);
	}
}
