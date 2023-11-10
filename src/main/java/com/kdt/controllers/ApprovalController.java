package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdt.dto.MembersDTO;
import com.kdt.dto.MembersDTO1;
import com.kdt.services.MembersService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/approval/document")
@Controller
public class ApprovalController {
	@Autowired
	private HttpSession session;
	@Autowired
	private MembersService mService;

	@RequestMapping("/write")
	public String write(Model model) throws Exception {
		MembersDTO1 userDTO = (MembersDTO1) session.getAttribute("userDTO");
		List<MembersDTO> approvalMembers = mService.selectApprovalMembers(userDTO.getPosition());
		model.addAttribute("userDTO", userDTO);
		model.addAttribute("approvalMembers", approvalMembers);
		
		System.out.println(approvalMembers);
		
		return "/approval/document/write";
	}
	
	@RequestMapping("/lists/all")
	public String listsAll(Model model) throws Exception {
		return "/approval/document/lists/all";
	}
	
	@RequestMapping(value="/left_item")
	public String toLeft_item(String selectItem, Model model) {
		model.addAttribute("selectItem", selectItem);
		
		return "/approval/document/left_item";
	}
}
