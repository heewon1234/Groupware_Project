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
	
	public List<SurveyDTO> voteList(int parent_seq,String board_title){
		SurveyDTO dto = new SurveyDTO();
		dto.setParent_seq(parent_seq);
		dto.setBoard_title(board_title);
		return sdao.voteList(dto);
	}
	
	@Transactional
	public List<SurveyDTO> voteCountUpdate(SurveyDTO dto,String id) {
		sdao.insertSurveyUser(dto, id);
		sdao.voteCountUpdate(dto.getSeq());
		return sdao.voteList(dto);
	}
	
	public boolean isVote(SurveyUserDTO dto) {
		return sdao.isVote(dto);
	}
	public boolean isExistVote(SurveyDTO dto) {
		return sdao.isExistVote(dto);
	}
}
