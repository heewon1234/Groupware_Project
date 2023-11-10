package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.AuthorityDAO;

@Service
public class AuthorityService {
	
	@Autowired
	AuthorityDAO adao;
	
	public List<String> selectAuthBoard(String id){
		return adao.selectAuthBoard(id);
	}
}
