package com.kdt.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.kdt.dao.BoardDAO;
import com.kdt.dao.FavoriteBoardDAO;
import com.kdt.dao.Mk_BoardDAO;
import com.kdt.dao.SurveyDAO;
import com.kdt.dto.BoardDTO;
import com.kdt.dto.FavoriteBoardDTO;
import com.kdt.dto.MembersDTO;
import com.kdt.dto.SurveyDTO;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO bdao;
	
	@Autowired
	Mk_BoardDAO mdao;
	
	@Autowired
	FavoriteBoardDAO fdao;
	
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
		String board_title = dto.getBoard_title();
		dto.setBoard_title("Board_"+boardSeq);
		int parent_seq = bdao.insertBoardContents(dto);
		for(String item:items) {
			if(item!="") {
				sdao.insertSurveyItem(new SurveyDTO(0,board_title,parent_seq,item,0,0));
			}
		}
		
	}
	
	// 게시글 불러오기 관련
	public List<BoardDTO> boardContentsList(String board_title, String id){ // 게시글 리스트
		Map<String,String> map = new HashMap<>();
		int boardSeq = mdao.selectBoardSeq(board_title);
		map.put("oriBoardTitle", board_title);
		map.put("board_title", "Board_"+boardSeq);
		map.put("id", id);
		return bdao.boardContentsList(map);
	}
	
	public List<BoardDTO> FavoriteAllContentsList(String board_title,String id){
		List<Integer> seqList = mdao.allBoardSeq();
		List<BoardDTO> favContentsList = new ArrayList<>();
		
		Map<String,String> map = new HashMap<>();
		map.put("id", id);
		for(int seq:seqList) {
			map.put("oriBoardTitle", mdao.selectBoardName(seq));
			map.put("board_title", "Board_"+seq);
			favContentsList.addAll(bdao.FavoriteAllContentsList(map));
		}
		return favContentsList;
	}
	
	public BoardDTO boardContents(String board_title, String seq) {		
		int boardSeq = mdao.selectBoardSeq(board_title);
		String sysBoardTitle = "Board_"+boardSeq;
		BoardDTO dto = new BoardDTO();
		dto.setBoard_title(sysBoardTitle);
		dto.setSeq(Integer.parseInt(seq));
		return bdao.boardContents(dto);
	}
	//
	
	// 게시글 삭제 관련
	@Transactional
	public void delContents(String seq, String board_title,String id) {
		int boardSeq = mdao.selectBoardSeq(board_title);
		
		Map<String,String> map = new HashMap<>();
		map.put("board_title", "Board_"+boardSeq);
		map.put("seq", seq);
		
		bdao.delContents(map);
		fdao.delFavContents(new FavoriteBoardDTO(0,"Board_"+boardSeq,id,Integer.parseInt(seq)));
	}
	//

}
