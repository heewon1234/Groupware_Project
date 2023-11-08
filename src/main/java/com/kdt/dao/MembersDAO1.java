package com.kdt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<MembersDTO1> getMembersByOrganization(String organization,String id){ 
		Map<String, Object> parameters = new HashMap<>();
	    parameters.put("organization", organization);
	    parameters.put("id", id);
		return db.selectList("Members1.getMembersByOrganization", parameters); 
	}
	public MembersDTO1 loginUser(String id){ 
		return db.selectOne("Members1.loginUser", id); 
	}
}
