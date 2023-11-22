package com.kdt.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.services.AuthorityService;
import com.kdt.services.MembersService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/authority/")
public class AuthorityController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	AuthorityService aservice;
	@Autowired
	MembersService mservice;
	
	@ResponseBody
	@RequestMapping("selectAuthMember")
	public List<Map<String,String>> selectAuthMember(String board_title){
		return aservice.selectAuthMember(board_title);
	}
	
	@ResponseBody
	@RequestMapping("isExistAuth")
	public boolean isExistAuth(String board_title){
		String id = (String)session.getAttribute("loginId");
		boolean admin = mservice.getJobName(id).equals("관리");
		boolean result = admin || aservice.isExistAuth(id,board_title);
		return result;
	}
	
	
}
