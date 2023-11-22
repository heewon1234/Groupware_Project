package com.kdt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.BoardDTO;
import com.kdt.dto.FavoriteBoardDTO;
import com.kdt.dto.MembersDTO;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSession db;



	// memberdao로 옮겨라

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

	// 조회수
	public int viewCountUpdate(Map<String,String> map) {
		return db.update("Board.viewCountUpdate",map);
	}

	// 게시글 불러오기
	public int boardContentsList(String board_title){
		return db.selectOne("Board.boardContentsList",board_title);
	}
	public List<BoardDTO> BoardContentsListBy(Map<String,String> map){
		return db.selectList("Board.BoardContentsListBy",map);
	}
	public BoardDTO boardContents(BoardDTO dto) {
		return db.selectOne("Board.boardContents",dto);
	}

	public int FavoriteAllContentsList(String id){
		return db.selectOne("Board.CountFavList",id);
	}
	public List<Map<String,String>> FavoriteListBy(Map<String,String> map){
		return db.selectList("Board.FavoriteListBy",map);
	}
	public List<BoardDTO> Notice(Map<String,String> map){
		return db.selectList("Board.Notice",map);
	}
	public List<BoardDTO> homeFavList(Map<String,String> map){
		return db.selectList("Board.homeFavList",map);
	}
	//

	// 게시글 검색
	public List<Map<String,String>> SearchFavoriteListBy(Map<String,String> map){
		return db.selectList("Board.SearchFavoriteListBy",map);
	}
	public int countSearchList(Map<String,String> map) {
		return db.selectOne("Board.countSearchList",map);
	}
	public List<BoardDTO> searchContentsListBy(Map<String,String> map){
		return db.selectList("Board.searchContentsListBy",map);
	}
	public int searchCountContentsListBy(Map<String,String> map) {
		return db.selectOne("Board.searchCountContentsListBy",map);
	}
	//게시글 삭제
	public int delContents(Map<String,String> map) {
		return db.delete("Board.delContents",map);
	}
	//

	// 게시글 수정
	public int updateContentsBoard(BoardDTO dto) {
		return db.update("Board.updateContentsBoard",dto);
	}
	public int updateSurveyQuestion(BoardDTO dto) {
		return db.update("Board.updateSurveyQuestion",dto);
	}
	//
}
