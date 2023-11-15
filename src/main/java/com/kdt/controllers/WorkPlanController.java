package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.dto.ApprovalDTO;
import com.kdt.dto.MembersDTO;
import com.kdt.services.JobTitleService;
import com.kdt.services.MembersService;
import com.kdt.services.OrganizationService;
import com.kdt.services.WorkPlanService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/works_plan/")
public class WorkPlanController {
	@Autowired
	private HttpSession session;
	@Autowired
	private MembersService mservice;
	@Autowired
	private OrganizationService orgService;
	@Autowired
	private JobTitleService jobService;
	@Autowired
	private WorkPlanService planService;

	@RequestMapping("/workPlan")
	public String towork_leave(Model model)throws Exception {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		List<String> managerOrgList = orgService.getManagerOrgList(userDTO.getOrganization());
		List<String> managerPositionList = jobService.getManagerPosition(userDTO.getPosition());
		List<MembersDTO> managerList = mservice.getManagerList(managerOrgList, managerPositionList);
		System.out.println("매니저"+managerList);
		System.out.println("매니저2"+managerPositionList);
		model.addAttribute("userDTO", userDTO);
		model.addAttribute("managerList", managerList);
		return "/insa/work_manage/workPlan";
	}
	@RequestMapping("getUserList")
	@ResponseBody
	public List<MembersDTO> getUserList(Model model)throws Exception {
		return mservice.getUserList();
	}
	
}