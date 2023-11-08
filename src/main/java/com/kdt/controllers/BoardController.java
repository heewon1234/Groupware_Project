package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdt.dto.Mk_BoardDTO;
import com.kdt.services.BoardService;

@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	BoardService bservice;
	
	@RequestMapping("sideBar")
	public String sideBar(Model model) {
		List<Mk_BoardDTO> group_list = bservice.select_board_type_group();
		List<Mk_BoardDTO> all_list = bservice.select_board_type_all();
		model.addAttribute("group_list",group_list);
		model.addAttribute("all_list",all_list);
		return "boards/sideBar";
	}
	
	@RequestMapping("toFavoriteBoard")
	public String toFavoriteBoard() {
		return "boards/favorite_board";
	}
	
	@RequestMapping("toMk_board")
	public String toMk_board(){
		return "boards/mk_board";
	}
	
	@RequestMapping("toEditBoard")
	public String toEditBoard() {
		return "boards/edit_board";
	}
	
	@RequestMapping("toContentsBoard")
	public String toContentsBoard() {
		return "boards/contents_board";
	}
	@RequestMapping("toWriteContentsBoard")
	public String toWriteContentsBoard() {
		return "boards/write_contents_board";
	}
}
