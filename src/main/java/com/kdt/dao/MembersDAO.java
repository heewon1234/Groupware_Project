package com.kdt.dao;

import java.util.HashMap;
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
	
	public String getOrganization(String id) {
		return db.selectOne("Members.getOrganization",id);
	}
	
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
	
	public List<MembersDTO> selectApprovalMembers(String position) {
		return db.selectList("Members.selectApprovalMembers", position);
	}
	
	public List<MembersDTO> selectAllNotLogged(String loggedInUserID){ 
		return db.selectList("Members.selectAllNotLogged",loggedInUserID); 
	}
	public List<MembersDTO> getMembersByOrganization(String organization,String id){ 
		Map<String, Object> parameters = new HashMap<>();
	    parameters.put("organization", organization);
	    parameters.put("id", id);
		return db.selectList("Members.getMembersByOrganization", parameters); 
	}
	public MembersDTO loginUser(String id){ 
		return db.selectOne("Members.loginUser", id); 
	}
	
	public MembersDTO getManager(String org, String position) {
		Map<String, Object> parameters = new HashMap<>();
	    parameters.put("org", org);
	    parameters.put("position", position);
		return db.selectOne("Members.getManager", parameters);
	}
}