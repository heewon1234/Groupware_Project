package com.kdt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.AuthorityDTO;
import com.kdt.dto.BoardDTO;
import com.kdt.dto.HeaderDTO;
import com.kdt.dto.MembersDTO;
import com.kdt.dto.Mk_BoardDTO;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSession db;

	
	
	// memberdao로 옮겨라
	public List<MembersDTO> selectAllMembers(){
		return db.selectList("Board.selectAllMembers");
	}
	
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

	
	// 게시글 등록 관련
	public int insertBoardContents(BoardDTO dto) {
		db.insert("insertBoardContents",dto);
		return dto.getSeq();
	}
	
	public int insertServeyItem(String item) {
		return db.insert("Board.insertServeyItem",item);
	}
	//
	
	// 게시글 불러오기
	public List<BoardDTO> boardContentsList(String board_title){
		return db.selectList("Board.boardContentsList",board_title);
	}
	
	public BoardDTO boardContents(Map<String,String> map) {
		return db.selectOne("Board.boardContents",map);
	}
	
	public List<BoardDTO> FavoriteAllContentsList(Map<String,String> map){
		return db.selectList("Board.FavoriteBoardContentsList",map);
	}
	//
	
	//게시글 삭제
	public int delContents(Map<String,String> map) {
		return db.delete("Board.delContents",map);
	}
	//
	
}
