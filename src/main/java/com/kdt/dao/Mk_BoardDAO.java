package com.kdt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.Mk_BoardDTO;

@Repository
public class Mk_BoardDAO {
	@Autowired
	private SqlSession db;
	
	
	public List<Mk_BoardDTO> select_board_type_group(String id){
		return db.selectList("Mk_Board.select_board_type_group",id);
	}
	
	public List<Mk_BoardDTO> select_board_type_all(String id){
		return db.selectList("Mk_Board.select_board_type_all",id);
	}
	
	public int selectBoardSeq(String board_title) {
		System.out.println(board_title);
		return db.selectOne("Mk_Board.selectBoardSeq",board_title);
	}
	
	public String selectBoardName(int seq) {
		return db.selectOne("Mk_Board.selectBoardName",seq);
	}
	
	public boolean isExistName(String board_title) {
		return db.selectOne("Mk_Board.isExistName",board_title);
	}
	
	//게시판 생성 관련
	public int Mk_boardInsert(Mk_BoardDTO dto) {
		return db.insert("Mk_Board.Mk_boardInsert",dto);
	}
	public int createTable(String sql) {
		return db.insert("Mk_Board.createTable",sql);
	}
	
	// 게시글 불러오기 관련 기능
	public String selectNameType(String board_title) {
		return db.selectOne("Mk_Board.selectNameType",board_title);
	}
	public List<Integer> allBoardSeq(){
		return db.selectList("Mk_Board.allBoardSeq");
	}
	
	// 게시판 목록 불러오기
	public List<Mk_BoardDTO> selectAllBoard(){
		return db.selectList("Mk_Board.selectAllBoard");
	}
	public Mk_BoardDTO boardDetail(String board_title) {
		return db.selectOne("Mk_Board.BoardDetail",board_title);
	}
	
	// 게시판 삭제
	public int delBoard(String sql) { // drop table
		return db.delete("Mk_Board.DropTable",sql);
	}
	// 게시판 관련 테이블에서 데이터 삭제
	public int deleteByBoardTitle(Map<String,String> map) { 
		return db.delete("Mk_Board.DeleteByBoardTitle",map);
	}
	public int deleteByOriBoardTitle(Map<String,String> map) { 
		return db.delete("Mk_Board.DeleteByOriBoardTitle",map);
	}
	
	// 게시판 수정
	public int editBoardDetail(Map<String,String> map) {
		return db.update("Mk_Board.editBoardDetail",map);
	
	}
	public int editBoardByTitle(Map<String,String> map) {
		return db.update("Mk_Board.editBoardByTitle",map);
	}
	public int editBoardByOriBoardTitle(Map<String,String> map) {
		return db.update("Mk_Board.editBoardByOriBoardTitle",map);
	}
	
}
