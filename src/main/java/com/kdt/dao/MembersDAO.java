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
	
	public List<MembersDTO> selectAllExceptMe(String id) {

		return db.selectList("Members.selectAllExceptMe", id);
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
	public List<MembersDTO> getUserList(){ 
		return db.selectList("Members.getUserList"); 
	}
	public List<String> getDepartmentList(){ 
		return db.selectList("Members.getDepartmentList"); 
	}
	public List<MembersDTO> getManager(String org, String position) {
		Map<String, Object> parameters = new HashMap<>();
	    parameters.put("org", org);
	    parameters.put("position", position);
		return db.selectList("Members.getManager", parameters);
	}
	public String getJobName(String id) {
		return db.selectOne("Members.getJobName",id);
	}
	public MembersDTO selectById(String id) {
		return db.selectOne("Members.selectById", id);
	}
	public int countUser() {
		return db.selectOne("Members.countUser");
	}
	public int updateMember(MembersDTO dto) {
		return db.update("Members.updateMember", dto);
	}
	
	// 게시판 권한 줄 때 사용
	public List<MembersDTO> selectAllMembers(){
		return db.selectList("Members.selectAllMembers");
	}

	public List<String> selectAllOrganization(){
		return db.selectList("Members.selectAllOrganization");
	}

	public List<String> selectByOrganization(String organization){
		return db.selectList("Members.selectByOrganization",organization);
	}

	public List<String> selectByJobName(MembersDTO dto){
		List<String> list = db.selectList("Members.selectByJobName",dto);
		return list;
	}

	public List<MembersDTO> selectMemberByOrganization(String organization){
		return db.selectList("Members.selectMemberByOrganization",organization);
	}

	public List<MembersDTO> selectMemberByOrganizationAndJobName(Map<String,String> map){
		return db.selectList("Members.selectMemberByOrganizationAndJobName",map);
	}

	public MembersDTO selectMemberByName(MembersDTO dto){
		MembersDTO list = db.selectOne("Members.selectMemberByName",dto);
		return list;
	}
	// 게시판 권한 줄 때 사용
}