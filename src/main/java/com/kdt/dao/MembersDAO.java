package com.kdt.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MembersDAO {
	@Autowired
	private SqlSession db;
	
	
	public boolean isMember(Map<String, String> param) {
		return db.selectOne("Members.isMember",param);
	}
}
