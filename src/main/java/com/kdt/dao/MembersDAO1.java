package com.kdt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.MembersDTO1;

@Repository
public class MembersDAO1 {
	@Autowired
	private SqlSession db;
	
	public List<MembersDTO1> selectAll(String loggedInUserID){ 
		return db.selectList("Members1.selectAll",loggedInUserID); 
	}
	public List<MembersDTO1> getMembersByOrganization(String organization){ 
		return db.selectList("Members1.getMembersByOrganization", organization); 
	}
	public MembersDTO1 loginUser(String id){ 
		return db.selectOne("Members1.loginUser", id); 
	}
}
