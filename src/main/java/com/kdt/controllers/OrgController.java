package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdt.dto.JobRoleDTO;
import com.kdt.dto.OrganizationDTO;
import com.kdt.services.OrganizationService;

@Controller
@RequestMapping("/org")
public class OrgController {
	@Autowired
	private OrganizationService service;
	
	@RequestMapping(value = "/insert")
	public String insertJobRole(OrganizationDTO dto) throws Exception {
		service.insert(dto);
		
		return "redirect:/insa/manage/org";
	}
	
	@RequestMapping(value = "/delete")
	public String deleteOrg(String organization) {
		service.delete(organization);
		
		return "redirect:/insa/manage/org";
	}
}
