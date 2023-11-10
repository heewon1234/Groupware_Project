package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdt.dto.ReplyDTO;
import com.kdt.services.ReplyService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reply/")
public class ReplyController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ReplyService rservice;
	
	@RequestMapping("insertReply")
	public String insertReply(ReplyDTO dto) {
		//dto.setWriter((String)session.getAttribute("loginID"));
		dto.setWriter("test1");
		dto.setBoard_title((String)session.getAttribute("board_title"));
		rservice.insertReply(dto);
		return "redirect:/board/toContentsBoard?seq="+dto.getParent_seq();
	}
}
