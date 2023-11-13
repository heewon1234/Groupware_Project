package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.dto.SurveyDTO;
import com.kdt.services.SurveyService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/survey/")
public class SurveyController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	SurveyService sservice;
	
	@ResponseBody
	@RequestMapping("voteCountUpdate")
	public List<SurveyDTO> voteCountUpdate(SurveyDTO dto) {	
		String id = (String)session.getAttribute("loginId");
		return sservice.voteCountUpdate(dto,id);
	}
	
	@ResponseBody
	@RequestMapping("voteList")
	public List<SurveyDTO> voteList(int parent_seq){
		return sservice.voteList(parent_seq);
	}
	
}
