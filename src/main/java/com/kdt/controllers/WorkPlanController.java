package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.dto.MembersDTO;
import com.kdt.services.MembersService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/works_plan/")
public class WorkPlanController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private MembersService mservice;
	
	@RequestMapping("/workPlan")
	public String towork_leave(Model model)throws Exception {
		return "/insa/work_manage/workPlan";
	}
	@RequestMapping("getUserList")
	@ResponseBody
	public List<MembersDTO> getUserList()throws Exception {
		return mservice.getUserList();
	}
	
}