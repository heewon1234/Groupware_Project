package com.kdt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.MembersDTO;

@Repository
public class MembersDAO {
	@Autowired
	private SqlSession db;
	
	
	public boolean isMember(Map<String, String> param) {
		return db.selectOne("Members.isMember",param);
	}
	
	public boolean idCheck(String id) {
		return db.selectOne("Members.idCheck",id);
	}
	
	public void signup(MembersDTO dto) {
		db.insert("Members.signup", dto);
	}
	
	//
	//
	//
	public List<MembersDTO> selectAll() {

		return db.selectList("Members.selectAll");
	}
	
	public int updateWorkForm(MembersDTO dto) {
		return db.update("Members.updateWorkForm", dto);
	}
	
	public int updatePosition(MembersDTO dto) {
		return db.update("Members.updatePosition", dto);
	}
	
	public int updateOrg(MembersDTO dto) {
		return db.update("Members.updateOrg", dto);
	}
	
	public int deleteMember(String id) {
		return db.delete("Members.deleteMember", id);
	}
	
	public List<String> selectWorkForm() {
		return db.selectList("Members.selectWorkForm");
	}
}
