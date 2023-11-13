package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdt.dto.BoardDTO;
import com.kdt.dto.Mk_BoardDTO;
import com.kdt.dto.ReplyDTO;
import com.kdt.dto.SurveyDTO;
import com.kdt.dto.SurveyUserDTO;
import com.kdt.services.AuthorityService;
import com.kdt.services.BoardService;
import com.kdt.services.Mk_BoardService;
import com.kdt.services.ReplyService;
import com.kdt.services.SurveyService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	BoardService bservice;
	
	@Autowired
	Mk_BoardService mservice;
	
	@Autowired
	AuthorityService aservice;
	
	@Autowired
	ReplyService rservice;
	
	@Autowired
	SurveyService sservice;
	
	@Autowired
	HttpSession session;
	
	// commons controller로 가라
	@RequestMapping("sideBar")
	public String sideBar(Model model) {
		List<Mk_BoardDTO> group_list = mservice.select_board_type_group();
		List<Mk_BoardDTO> all_list = mservice.select_board_type_all();
		model.addAttribute("group_list",group_list);
		model.addAttribute("all_list",all_list);
		return "boards/sideBar";
	}	
	//
	
	
	// R 관련 기능
	@RequestMapping("toBoard") // 게시글 리스트 보는 곳 이동
	public String toBoard(String board_title, Model model) {
		
		if(board_title==null) {
			if(session.getAttribute("board_title")==null) {
				board_title="중요게시물";
			} else {
				board_title=(String)session.getAttribute("board_title");
			}
			
		}
		String id = (String)session.getAttribute("loginId");
		session.setAttribute("board_title", board_title);
		List<BoardDTO> boardContentsList;
		if(board_title.equals("중요게시물")) {
			boardContentsList = bservice.FavoriteAllContentsList(board_title,id);
		} else {
			boardContentsList = bservice.boardContentsList(board_title,id);
		}
		
		model.addAttribute("boardContentsList",boardContentsList);
		return "boards/contentsList_board";
	}
	
	@RequestMapping("toContentsBoard") // 게시글 내용 보는 곳으로 이동
	public String toContentsBoard(String seq, String board_title, Model model) {
		if(board_title==null && (String)session.getAttribute("board_title")!="중요게시물") {
			board_title= (String)session.getAttribute("board_title");
		}
		
		List<ReplyDTO> replyList = rservice.replyList(board_title, seq);
		model.addAttribute("replyList",replyList);
		
		BoardDTO boardContents = bservice.boardContents(board_title, seq);
		model.addAttribute("boardContents",boardContents);
		
		model.addAttribute("surveyList", null);
		model.addAttribute("isVote",false);
		
		String id = (String)session.getAttribute("loginId");
		
		if(!boardContents.getSurvey_question().isEmpty()) {
			model.addAttribute("isVote",sservice.isVote(new SurveyUserDTO (0,id,Integer.parseInt(seq))));
			List<SurveyDTO> list = sservice.selectServeyItem(new SurveyDTO(0,board_title, Integer.parseInt(seq),null,0,0));
			model.addAttribute("surveyList", list);
		}

		return "boards/contents_board";
	}
	
	// C 관련 기능
	@RequestMapping("toWriteContentsBoard") // 게시글 작성하는 곳 이동
	public String toWriteContentsBoard(Model model) {
		String id = (String)session.getAttribute("loginId");
		List<String> boardList = aservice.selectAuthBoard(id);
		model.addAttribute("boardList",boardList);
		return "boards/write_contents_board";
	}

	@RequestMapping("insertBoardContents") // 게시글 등록
	public String insertBoardContents(BoardDTO dto,String[] items) {
		String writer = (String)session.getAttribute("loginId");
		dto.setWriter(writer);
		bservice.insertBoardContents(dto, items);
		
		String board_title=(String)session.getAttribute("board_title");
		return "redirect:/board/toBoard";	
	}
	
	// U 관련 기능
	@RequestMapping("toEditContentsBoard") // 게시판 수정하는 곳 이동
	public String toEditBoard() {
		return "boards/edit_contents_board";
	}
	
	// D 관련 기능
	@RequestMapping("toDelContents")
	public String toDelContents() {
		return "boards/delContents";
	}
	
	@RequestMapping("delContents")
	public String delContents(String seq) {
		String board_title = (String)session.getAttribute("board_title");
		String id = (String)session.getAttribute("loginId");
		bservice.delContents(seq, board_title,id);
		return "redirect:/board/toBoard";
	}
	
	
}
