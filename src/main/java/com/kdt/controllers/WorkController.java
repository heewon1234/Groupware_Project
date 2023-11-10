package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.dto.LeavesDTO;
import com.kdt.dto.WorkTimesDTO;
import com.kdt.dto.WorksDTO;
import com.kdt.dto.WorkstatisticsDTO;
import com.kdt.services.WorksService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/works/")
public class WorkController {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private WorksService wservice;
	
	
	@RequestMapping("work_leave")
	public String towork_leave(Model model)throws Exception {
		String ID = (String) session.getAttribute("loginId");
		List<WorksDTO> list = wservice.select(ID);
		model.addAttribute("list",list);
		return "/insa/mywork/work_leave";
	}
	@RequestMapping("workmanage")
	public String toworkmanage(Model model)throws Exception {
		return "/insa/work_manage/workmanage";
	}
	@RequestMapping("workstatistics")
	public String toworkstatistics(Model model)throws Exception {
		return "/insa/work_manage/workstatistics";
	}
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
	
	@ResponseBody
	@RequestMapping("work_inout") // 출근시간 퇴근시간 시간 출력
	public List<WorkTimesDTO> work_inout(Model model)throws Exception  {
		String ID = (String) session.getAttribute("loginId");
		List<WorkTimesDTO> list = wservice.work_inout(ID);
		model.addAttribute("list",list);
		return list;
	}
	@RequestMapping("left_item")
	public String toLeft_item(String selectItem, Model model) {
		model.addAttribute("selectItem", selectItem);
		return "/insa/mywork/left_item";
	}
	@RequestMapping("manage_left_item")
	public String tomanage_Left_item(String selectItem, Model model) {
		model.addAttribute("selectItem", selectItem);
		return "/insa/work_manage/left_item";
	}
	@ResponseBody
	@RequestMapping("statisticslist") // 근무통계 출력
	public List<WorkstatisticsDTO> statisticslist(Model model)throws Exception  {
		List<WorkstatisticsDTO> list = wservice.statisticslist();
		model.addAttribute("list",list);
		return list;
	}

	@ResponseBody
	@RequestMapping("workmanagelist") // 근무통계 출력
	public List<LeavesDTO> workmanage(Model model)throws Exception  {
		List<LeavesDTO> list = wservice.leaveslist();
		model.addAttribute("list",list);
		return list;
	}
	
	
	

}
