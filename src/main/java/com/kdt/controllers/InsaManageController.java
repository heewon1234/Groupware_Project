package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdt.dto.JobRoleDTO;
import com.kdt.dto.JobTitleDTO;
import com.kdt.services.JobRoleService;
import com.kdt.services.JobTitleService;

@Controller
@RequestMapping("/insa/manage")
public class InsaManageController {
	@Autowired
	private JobRoleService jrService;
	@Autowired
	private JobTitleService jtService;
//	@Autowired
//	private OrganizationService orgService;
//	@Autowired
//	private MembersService mService;

	@RequestMapping(value = "/job")
	public String home(Model model) throws Exception {
		List<JobRoleDTO> jobRoleList = jrService.selectAll();
		List<JobTitleDTO> jobTitleList = jtService.selectAll();

		model.addAttribute("jobRoleList", jobRoleList);
		model.addAttribute("jobTitleList", jobTitleList);
		return "/insa/manage/job";
	}
	
//	@RequestMapping(value="/org")
//	public String toOrg(Model model) {
//		List<OrganizationDTO> orgList = orgService.selectAll();
//		
//		model.addAttribute("orgList", orgList);
//		
//		return "/insa/manage/organization";
//	}
//	
//	@RequestMapping(value="/members")
//	public String toMember(Model model) {
//		List<JobTitleDTO> jobTitleList = jtService.selectAll();
//		List<OrganizationDTO> orgList = orgService.selectAll();
//		List<MembersDTO> membersList = mService.selectAll();
//		
//		model.addAttribute("jobTitleList", jobTitleList);
//		model.addAttribute("orgList", orgList);
//		model.addAttribute("membersList", membersList);
//		
//		return "/insa/manage/members";
//	}
	
	@RequestMapping(value="/left_item")
	public String toLeft_item(String selectItem, Model model) {
		model.addAttribute("selectItem", selectItem);
		return "/insa/manage/left_item";
	}
}
