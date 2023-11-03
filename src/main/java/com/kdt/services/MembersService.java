package com.kdt.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.MembersDAO;

@Service
public class MembersService {
	@Autowired
	public MembersDAO mdao;
	
	public boolean isMember(String id, String pw) {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("pw", pw);
		return this.mdao.isMember(param);
	}
}
