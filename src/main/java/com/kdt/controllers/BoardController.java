package com.kdt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@RequestMapping("sideBar")
	public String sideBar() {
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
}
