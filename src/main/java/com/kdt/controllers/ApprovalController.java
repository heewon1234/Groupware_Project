package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.dto.ApprovalDTO;
import com.kdt.dto.MembersDTO;
import com.kdt.dto.MembersDTO1;
import com.kdt.services.ApprovalService;
import com.kdt.services.MembersService;
import com.kdt.services.OrganizationService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/approval/document")
@Controller
public class ApprovalController {
	@Autowired
	private HttpSession session;
	@Autowired
	private ApprovalService appService;
	@Autowired
	private OrganizationService orgService;
	

	@RequestMapping("/write")
	public String write(Model model) throws Exception {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		List<String> managerList = orgService.getManagerList(userDTO.getOrganization());
		model.addAttribute("userDTO", userDTO);
		
		System.out.println(managerList);
		
		return "/approval/document/write";
	}
	
	@RequestMapping("/lists/all")
	public String listsAll(Model model) throws Exception {
		MembersDTO1 userDTO = (MembersDTO1) session.getAttribute("userDTO");
		List<ApprovalDTO> appList = appService.selectById(userDTO.getId());
		
		model.addAttribute("appList", appList);
		
		return "/approval/document/lists/all";
	}
	
	@RequestMapping("/lists/wait")
	public String listsWait(Model model) throws Exception {
		MembersDTO1 userDTO = (MembersDTO1) session.getAttribute("userDTO");
		List<ApprovalDTO> appList = appService.selectWaitById(userDTO.getId());
		
		model.addAttribute("appList", appList);
		
		return "/approval/document/lists/wait";
	}
	
	@RequestMapping("/insertApproval")
	public String insertApproval(String title, String contents, MultipartFile[] files) throws Exception {
		MembersDTO1 userDTO = (MembersDTO1) session.getAttribute("userDTO");
		ApprovalDTO appdto = new ApprovalDTO(0, userDTO.getId(), title, contents, null, "Wait");
		String uploadPath = "c:/uploads";
		
		System.out.println(files);
		
		appService.insert(appdto, files, uploadPath);
		
		return "redirect:/approval/document/lists/all";
	}
	
	@RequestMapping(value="/left_item")
	public String toLeft_item(String selectItem, Model model) {
		model.addAttribute("selectItem", selectItem);
		
		return "/approval/document/left_item";
	}
}
