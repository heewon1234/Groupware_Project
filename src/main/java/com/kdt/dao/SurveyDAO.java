package com.kdt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.SurveyDTO;
import com.kdt.dto.SurveyUserDTO;

@Repository
public class SurveyDAO {
	
	@Autowired
	private SqlSession db;
	
	public int insertSurveyItem(SurveyDTO dto) {
		return db.insert("Survey.insertSurveyItem",dto);
	}
	
	public List<SurveyDTO> selectServeyItem(SurveyDTO dto){
		return db.selectList("Survey.selectServeyItem",dto);
	}
	
	public int insertSurveyUser(SurveyDTO dto, String id) {
		return db.insert("Survey.insertSurveyUser",new SurveyUserDTO(0,id,dto.getParent_seq(),dto.getBoard_title()));
	}
	
	public int voteCountUpdate(int seq) {
		return db.update("Survey.voteCountUpdate",seq);
	}
	
	public boolean isVote(SurveyUserDTO dto) {
		return db.selectOne("Survey.isVote",dto);
	}
	
	public List<SurveyDTO> voteList(int parent_seq){
		return db.selectList("Survey.voteList",parent_seq);
	}
	
	public int delSurveyByParentSeq(SurveyDTO dto) {
		return db.delete("Survey.delSurveyByParentSeq",dto);
	}
	
	public int delSurveyUserByParentSeq(SurveyUserDTO dto) {
		return db.delete("Survey.delSurveyUserByParentSeq",dto);
	}
	
}
