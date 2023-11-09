package com.kdt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.AuthorityDTO;
import com.kdt.dto.HeaderDTO;
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
	
	//게시판 생성 관련
	public int Mk_boardInsert(Mk_BoardDTO dto) {
		dto.setId("test1");
		return db.insert("Board.Mk_boardInsert",dto);
	}
	public int createTable(String sql) {
		return db.insert("Board.createTable",sql);
	}
	public int authorityInsert(AuthorityDTO dto) {
		return db.insert("Board.authorityInsert",dto);
	}
	
	public int headerInsert(HeaderDTO dto) {
		return db.insert("Board.headerInsert",dto);
	}
	//

}
