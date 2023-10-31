package com.kdt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards/")
public class BoardController {
	
	@RequestMapping("/board")
	public String board() {
		return "boards/board";
	}
}
