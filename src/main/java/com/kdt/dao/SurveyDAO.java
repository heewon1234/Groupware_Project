package com.kdt.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.SurveyDTO;

@Repository
public class SurveyDAO {
	
	@Autowired
	private SqlSession db;
	
	public int insertSurveyItem(SurveyDTO dto) {
		return db.insert("Survey.insertSurveyItem",dto);
	}
	
}
