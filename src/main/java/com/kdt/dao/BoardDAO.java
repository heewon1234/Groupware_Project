package com.kdt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.MembersDTO;
import com.kdt.dto.Mk_BoardDTO;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSession db;

	public List<Mk_BoardDTO> select_board_type_group(){
		return db.selectList("Board.select_board_type_group");
	}
	
	public List<Mk_BoardDTO> select_board_type_all(){
		return db.selectList("Board.select_board_type_all");
	}
	
	// memberdao로 옮겨라
	public List<String> selectAllOrganization(){
		return db.selectList("Board.selectAllOrganization");
	}
	
	public List<String> selectByOrganization(String organization){
		return db.selectList("Board.selectByOrganization",organization);
	}
	
	public List<String> selectByJobName(String job_name){
		List<String> list = db.selectList("Board.selectByJobName",job_name);
		System.out.println(list.size());
		return list;
	}
	
	public List<MembersDTO> selectMemberByOrganization(String organization){
		return db.selectList("Board.selectMemberByOrganization",organization);
	}
	
	public List<MembersDTO> selectMemberByOrganizationAndJobName(Map<String,String> map){
		return db.selectList("Board.selectMemberByOrganizationAndJobName",map);
	}
	
	public MembersDTO selectMemberByName(MembersDTO dto){
		MembersDTO list = db.selectOne("Board.selectMemberByName",dto);
		System.out.println(dto.getJob_name());
		return list;
	}
	//
}
