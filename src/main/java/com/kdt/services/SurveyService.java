package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdt.dao.SurveyDAO;
import com.kdt.dto.SurveyDTO;
import com.kdt.dto.SurveyUserDTO;

@Service
public class SurveyService {
	
	@Autowired
	SurveyDAO sdao;
	
	public List<SurveyDTO> selectServeyItem(SurveyDTO dto){
		return sdao.selectServeyItem(dto);
	}
	
	public List<SurveyDTO> voteList(int parent_seq){
		return sdao.voteList(parent_seq);
	}
	
	@Transactional
	public List<SurveyDTO> voteCountUpdate(SurveyDTO dto,String id) {
		sdao.insertSurveyUser(dto, id);
		sdao.voteCountUpdate(dto.getSeq());
		return sdao.voteList(dto.getParent_seq());
	}
	
	public boolean isVote(SurveyUserDTO dto) {
		return sdao.isVote(dto);
	}
}
