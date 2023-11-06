package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdt.dto.JobRoleDTO;
import com.kdt.dto.JobTitleDTO;
import com.kdt.services.JobRoleService;
import com.kdt.services.JobTitleService;

@Controller
@RequestMapping("/job")
public class JobController {
	@Autowired
	private JobRoleService jrService;
	@Autowired
	private JobTitleService jtService;

	@RequestMapping(value = "/insertJobRole")
	public String insertJobRole(JobRoleDTO dto) throws Exception {
		if(dto.getJob_name() != null) {
			jrService.insert(dto);
		}

		return "redirect:/insa/manage/job";
	}

	@RequestMapping(value = "/deleteJobRole")
	public String deleteJobRole(JobRoleDTO dto) throws Exception {
		jrService.delete(dto);

		return "redirect:/insa/manage/job";
	}

	@RequestMapping(value = "/insertJobTitle")
	public String insertJobTitle(JobTitleDTO dto) {
		if(dto.getPosition() != null) {
			jtService.insert(dto);
		}

		return "redirect:/insa/manage/job";
	}

	@RequestMapping(value = "/deleteJobTitle")
	public String insertJobTitle(String position) {
		jtService.delete(position);

		return "redirect:/insa/manage/job";
	}


}
