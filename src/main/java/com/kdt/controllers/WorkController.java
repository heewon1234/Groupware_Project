package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.dto.WorkTimesDTO;
import com.kdt.services.WorksService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/works/")
public class WorkController {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private WorksService wservice;
	
	@ResponseBody
	@RequestMapping("insert") //출근시간 퇴근시간 삽입
	public List<WorkTimesDTO> insert(WorkTimesDTO dto,Model model)throws Exception {
		String ID = (String) session.getAttribute("loginId");
		dto.setId(ID);
		wservice.insert(dto);
		List<WorkTimesDTO> tlist = wservice.selectby(ID);
		model.addAttribute("tlist",tlist);
		return tlist;
	}
	@ResponseBody
	@RequestMapping("list") // 근무현황판 출력
	public List<WorkTimesDTO> list(Model model)throws Exception  {
		String ID = (String) session.getAttribute("loginId");
		List<WorkTimesDTO> tlist = wservice.selectby(ID);
		model.addAttribute("tlist",tlist);
		return tlist;
	}
	
	@RequestMapping("toworkmanage") // 전사근무관리 이동
	public String toworkmanage() {
		return "workmanage";
	}
	
	@ResponseBody
	@RequestMapping("work_inout") // 출근시간 퇴근시간 시간 출력
	public List<WorkTimesDTO> work_inout(Model model)throws Exception  {
		String ID = (String) session.getAttribute("loginId");
		List<WorkTimesDTO> list = wservice.work_inout(ID);
		model.addAttribute("list",list);
		return list;
	}
	
	
	

}
