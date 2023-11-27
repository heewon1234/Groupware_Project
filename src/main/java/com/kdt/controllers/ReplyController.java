package com.kdt.controllers;

import java.sql.Timestamp;

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
		dto.setWriter((String)session.getAttribute("loginId"));
		String contents = dto.getContents().replaceAll("\n", "<br>");
		dto.setContents(contents);
		dto.setWrite_date(new Timestamp(System.currentTimeMillis()));
		dto.setBoard_title((String)session.getAttribute("board_title"));
		rservice.insertReply(dto);
		return "redirect:/board/toContentsBoard?seq="+dto.getParent_seq();
	}
	
	@RequestMapping("toDelReply")
	public String toDelReply() {
		return "boards/delReply";
	}
	@RequestMapping("delReply")
	public String delReply(ReplyDTO dto) {
		String board_title = (String)session.getAttribute("board_title");
		dto.setBoard_title(board_title);
		rservice.delReply(dto);
		return "redirect:/board/toContentsBoard?seq="+dto.getParent_seq();
	}
	
	@RequestMapping("updateReply")
	public String updateReply(ReplyDTO dto) {
		System.out.println(dto.getSeq());
		dto.setBoard_title((String)session.getAttribute("board_title"));
		rservice.updateReply(dto);
		return "redirect:/board/toContentsBoard?seq="+dto.getParent_seq();
	}
}
