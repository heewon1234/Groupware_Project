package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdt.services.MembersService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/members/")
public class MembersController {
	@Autowired
	private MembersService mservice;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("login")
	public String login(String id, String pw) throws Exception{
		//String shapw = EncryptionUtils.getSHA512(pw);
		boolean result = this.mservice.isMember(id,pw);
		if(result) {
			session.setAttribute("loginId",id);
			return "home";
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("logout")
	public String logout() throws Exception{
		session.invalidate();
		return "redirect:/";
	}
}
