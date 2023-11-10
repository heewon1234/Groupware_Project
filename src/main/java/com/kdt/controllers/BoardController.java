package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdt.dto.BoardDTO;
import com.kdt.dto.Mk_BoardDTO;
import com.kdt.services.AuthorityService;
import com.kdt.services.BoardService;
import com.kdt.services.Mk_BoardService;

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
	
	// 게시글 리스트 보는 곳
	@RequestMapping("toBoard")
	public String toFavoriteBoard(String board_title, Model model) {
		session.setAttribute("board_title", board_title);
		List<BoardDTO> boardContentsList = bservice.boardContentsList(board_title);
		model.addAttribute("boardContentsList",boardContentsList);
		return "boards/contentsList_board";
	}
	//
	
	// 게시글 등록 관련
	@RequestMapping("toWriteContentsBoard")
	public String toWriteContentsBoard(Model model) {
		//String id = (String)session.getAttribute("loginID");
		String id = "test1";
		List<String> boardList = aservice.selectAuthBoard(id);
		model.addAttribute("boardList",boardList);
		return "boards/write_contents_board";
	}

	@RequestMapping("insertBoardContents")
	public String insertBoardContents(BoardDTO dto,String[] items) {
		//String writer = (String)session.getAttribute("loginID");
		dto.setWriter("test1");
		bservice.insertBoardContents(dto, items);
		return "redirect:/board/toFavoriteBoard";	
	}
	//
	
	@RequestMapping("toEditBoard")
	public String toEditBoard() {
		return "boards/edit_board";
	}
	
	@RequestMapping("toContentsBoard")
	public String toContentsBoard() {
		return "boards/contents_board";
	}
	
}
