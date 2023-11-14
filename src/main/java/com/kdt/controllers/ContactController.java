package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.dto.ContactDTO;
import com.kdt.services.ContactService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/contact/")
@Controller
public class ContactController {
	
	@Autowired
	private ContactService Service;
	
	@Autowired
	private HttpSession session;

	@RequestMapping("personal")
	public String personal(Model model) {
		ContactDTO dto = new ContactDTO();
		String id = (String) session.getAttribute("loginID");
		dto.setWriter("test1");
		
		List<ContactDTO> personalList = Service.personalContactTagSelectAll(dto);
		List<ContactDTO> shareList = Service.shareContactTagSelectAll(dto);
		
		model.addAttribute("personalList", personalList);
	    model.addAttribute("shareList", shareList);
		
		return "contact/personal";
	}
	
	@ResponseBody
	@RequestMapping("personalContactTagSelectAll")
	public List<ContactDTO> personalContactTagSelectAll(ContactDTO dto) {
		String id = (String) session.getAttribute("loginID");
		dto.setWriter("test1");
		return this.Service.personalContactTagSelectAll(dto);
	}
	
	@ResponseBody
	@RequestMapping("personalContactTagSelectAllDeplicate")
	public List<ContactDTO> personalContactTagSelectAllDeplicate(ContactDTO dto) {
		String id = (String) session.getAttribute("loginID");
		dto.setWriter("test1");
		return Service.personalContactTagSelectAllDeplicate(dto);
	}
	
	@ResponseBody
	@RequestMapping("personalContactTagInsert")
	public int personalContactTagInsert(ContactDTO dto) {
		String id = (String) session.getAttribute("loginID");
		dto.setWriter("test1");
		return Service.personalContactTagInsert(dto);
	}
	
	
	@RequestMapping("personalContactInsert")
	public String personalContactInsert(ContactDTO dto) throws Exception{
		String id = (String) session.getAttribute("loginId");
		dto.setWriter("test1");
		Service.personalContactInsert(dto);
		return "contact/personal";
	}
	
	@ResponseBody
	@RequestMapping("shareContactTagSelectAll")
	public List<ContactDTO> shareContactTagSelectAll(ContactDTO dto) {
		return this.Service.shareContactTagSelectAll(dto);
	}
	
	@RequestMapping("shareContactInsert")
	public String shareContactInsert(ContactDTO dto) throws Exception{
		String id = (String) session.getAttribute("loginId");
		dto.setWriter("test1");
		Service.shareContactInsert(dto);
		return "contact/personal";
	}
	
}
