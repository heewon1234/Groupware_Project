package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdt.dto.BoardDTO;
import com.kdt.dto.Official_CalendarDTO;
import com.kdt.services.BoardService;
import com.kdt.services.CalendarService;
import com.kdt.services.MembersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private CalendarService cservice;
	
	@Autowired
	private MembersService mservice;
	
	@Autowired
	private BoardService bservice;
	
	
	@RequestMapping("/")
	public String login(Model model) throws Exception{
		String id = (String) session.getAttribute("loginId");
		if(id != null) {
			String org = this.mservice.getOrganization(id);
			List<Official_CalendarDTO> list = this.cservice.selectAllO(org);
			List<BoardDTO> favBoardList = bservice.homeFavList(id);
			model.addAttribute("favBoardList",favBoardList);
			model.addAttribute("list",list);
			return "home";
			
		}
		
		return "home";
	}
	
	@RequestMapping("/err")
	public String err() {
		return "error";
	}
	
}
