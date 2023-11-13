package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdt.dto.JobRoleDTO;
import com.kdt.dto.JobTitleDTO;
import com.kdt.dto.MembersDTO;
import com.kdt.dto.OrganizationDTO;
import com.kdt.services.JobRoleService;
import com.kdt.services.JobTitleService;
import com.kdt.services.MembersService;
import com.kdt.services.OrganizationService;
import com.kdt.services.WorksService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/insa/manage")
public class InsaManageController {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private WorksService wservice;
	@Autowired
	private JobRoleService jrService;
	@Autowired
	private JobTitleService jtService;
	@Autowired
	private OrganizationService orgService;
	@Autowired
	private MembersService mService;

	@RequestMapping(value = "/job")
	public String home(Model model) throws Exception {
		List<JobRoleDTO> jobRoleList = jrService.selectAll();
		List<JobTitleDTO> jobTitleList = jtService.selectAll();

		model.addAttribute("jobRoleList", jobRoleList);
		model.addAttribute("jobTitleList", jobTitleList);
		return "/insa/manage/job";
	}
	
	@RequestMapping(value="/org")
	public String toOrg(Model model) {
		List<OrganizationDTO> orgList = orgService.selectAll();
		
		model.addAttribute("orgList", orgList);
		
		return "/insa/manage/organization";
	}
	
	@RequestMapping(value="/members")
	public String toMember(Model model) {
		String id = (String) session.getAttribute("loginId");
		MembersDTO userDTO = mService.loginUser(id);
		String position = userDTO.getPosition();
		int rank = jtService.getRank(position);
		
		List<JobTitleDTO> jobTitleList = jtService.selectAll();
		List<JobRoleDTO> jobRoleList = jrService.selectAll();
		List<OrganizationDTO> orgList = orgService.selectAll();
		List<MembersDTO> membersList = mService.selectAll();
		List<String> workFormList = mService.selectWorkForm();
		
		model.addAttribute("rank",rank);
		model.addAttribute("jobTitleList", jobTitleList);
		model.addAttribute("jobRoleList", jobRoleList);
		model.addAttribute("orgList", orgList);
		model.addAttribute("membersList", membersList);
		model.addAttribute("workFormList", workFormList);
		
		return "/insa/manage/members";
	}
	
	@RequestMapping(value="/left_item")
	public String toLeft_item(String selectItem, Model model) {
		model.addAttribute("selectItem", selectItem);
		return "/insa/manage/left_item";
	}
	
}
