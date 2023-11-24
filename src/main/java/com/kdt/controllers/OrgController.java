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
	public String insertJobRole(String organization, String manager) throws Exception {
		int level = service.getOrgLevel(manager) + 1;
		
		service.insert(new OrganizationDTO(organization, level, manager, 0));
		
		return "redirect:/insa/manage/org";
	}
	
	@RequestMapping(value = "/delete")
	public String deleteOrg(String organization) {
		service.delete(organization);
		
		return "redirect:/insa/manage/org";
	}
}
