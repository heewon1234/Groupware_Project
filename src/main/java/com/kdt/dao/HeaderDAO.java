package com.kdt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.HeaderDTO;

@Repository
public class HeaderDAO {
	
	@Autowired
	private SqlSession db;
	
	public int headerInsert(HeaderDTO dto) {
		return db.insert("Header.headerInsert",dto);
	}
	
	public List<String> selectHeader(String board_title){
		return db.selectList("Header.selectHeader",board_title);
	}
}
