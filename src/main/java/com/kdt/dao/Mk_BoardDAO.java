package com.kdt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.Mk_BoardDTO;

@Repository
public class Mk_BoardDAO {
	@Autowired
	private SqlSession db;
	
	public List<Mk_BoardDTO> select_board_type_group(){
		return db.selectList("Mk_Board.select_board_type_group");
	}
	
	public List<Mk_BoardDTO> select_board_type_all(){
		return db.selectList("Mk_Board.select_board_type_all");
	}
	
	public int selectBoardSeq(String board_title) {
		return db.selectOne("Mk_Board.selectBoardSeq",board_title);
	}
	
	public String selectBoardName(int seq) {
		return db.selectOne("Mk_Board.selectBoardName",seq);
	}
	
	//게시판 생성 관련
	public int Mk_boardInsert(Mk_BoardDTO dto) {
		return db.insert("Mk_Board.Mk_boardInsert",dto);
	}
	public int createTable(String sql) {
		return db.insert("Mk_Board.createTable",sql);
	}
	
	//게시글 불러오기 관련 기능
	public List<Integer> allBoardSeq(){
		return db.selectList("Mk_Board.allBoardSeq");
	}
}
