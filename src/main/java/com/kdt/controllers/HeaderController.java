package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.services.HeaderService;

@Controller
@RequestMapping("/header/")
public class HeaderController {
	
	@Autowired
	HeaderService hservice;
	
	@ResponseBody
	@RequestMapping("selectHeader")
	public List<String> selectHeader(String board_title){
		return hservice.selectHeader(board_title);
	}
}
