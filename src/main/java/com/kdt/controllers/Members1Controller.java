package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.dto.MembersDTO1;
import com.kdt.services.MembersService1;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/members1/")
public class Members1Controller {
	@Autowired
	private MembersService1 mservice;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("selectAll")
	@ResponseBody
	public List<MembersDTO1> selectAll() throws Exception {
	    List<MembersDTO1> list = mservice.selectAll();
	    return list;
	}

}
