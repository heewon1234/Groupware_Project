package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.HeaderDAO;

@Service
public class HeaderService {
	
	@Autowired
	HeaderDAO hdao;
	
	public List<String> selectHeader(String board_title){
		return hdao.selectHeader(board_title);
	}
	
}
