package com.kdt.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.kdt.dao.BoardDAO;
import com.kdt.dao.Mk_BoardDAO;
import com.kdt.dao.SurveyDAO;
import com.kdt.dto.BoardDTO;
import com.kdt.dto.MembersDTO;
import com.kdt.dto.SurveyDTO;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO bdao;
	
	@Autowired
	Mk_BoardDAO mdao;
	
	@Autowired
	SurveyDAO sdao;
	
	@Autowired
	private Gson gson;

	// MemberService로 가시오
	public List<MembersDTO> selectAllMembers(){
		return bdao.selectAllMembers();
	}
	public List<String> selectAllOrganization(){
		return bdao.selectAllOrganization();
	}
	
	public List<String> selectByOrganization(String organization){
		return bdao.selectByOrganization(organization);
	}
	
	public List<String> selectByJobName(String job_name){
		return bdao.selectByJobName(job_name);
	}
	public List<MembersDTO> selectMemberByOrganization(String organization){
		return bdao.selectMemberByOrganization(organization);
	}
	
	public List<MembersDTO> selectMemberByOrganizationAndJobName(String organization,String job_name){
		Map<String,String> map = new HashMap<>();
		map.put("organization", organization);
		map.put("job_name", job_name);
		return bdao.selectMemberByOrganizationAndJobName(map);
	}
	
	public MembersDTO selectMemberByName(MembersDTO dto){
		return bdao.selectMemberByName(dto);
	}
	//
	
	// 게시글 등록 관련
	@Transactional
	public void insertBoardContents(BoardDTO dto,String[] items) {
		int boardSeq = mdao.selectBoardSeq(dto.getBoard_title());
		dto.setBoard_title("Board_"+boardSeq);
		int parent_seq = bdao.insertBoardContents(dto);
		for(String item:items) {
			if(item!="") {
				sdao.insertSurveyItem(new SurveyDTO(0,dto.getBoard_title(),parent_seq,item));
			}
		}
		
	}
	//
	
	// 게시글 리스트 불러오기
	public List<BoardDTO> boardContentsList(String board_title){
		int boardSeq = mdao.selectBoardSeq(board_title);
		return bdao.boardContentsList("Board_"+boardSeq);
	}
	
}
