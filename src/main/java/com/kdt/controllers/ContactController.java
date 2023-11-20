package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String personal(ContactDTO dto, Model model) {
		String id = (String) session.getAttribute("loginID");
		dto.setWriter("test1");
		
		List<ContactDTO> personalTagList = Service.personalContactTagSelectAll(dto);
		List<ContactDTO> shareTagList = Service.shareContactTagSelectAll(dto);
		
		model.addAttribute("personalTagList", personalTagList);
	    model.addAttribute("shareTagList", shareTagList);
	   
	    if(dto.getTag() == null) {
	    	List<ContactDTO> personalContactList = Service.personalContactSelectAll(dto);
	    	model.addAttribute("personalContactList", personalContactList);
	    	model.addAttribute("contactName", "개인 주소록");
	    }
	    
	    else {
	    	
	    	if(dto.getTag().equals("미등록 태그")) {
	    		System.out.println("미태다");
	    		List<ContactDTO> personalContactList = Service.personalContactSelectNull(dto);
	    		model.addAttribute("personalContactList", personalContactList);
		    	model.addAttribute("contactName", dto.getTag());
	    	}
	    	
	    	else {
		    	List<ContactDTO> personalContactList = Service.personalContactSelectBy(dto);
		    	model.addAttribute("personalContactList", personalContactList);
		    	model.addAttribute("contactName", dto.getTag());
	    	}
	    }
	    
		
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
	
	@RequestMapping("personalContactTagInsertDirect")
	public String personalContactTagInsertDirect(ContactDTO dto) {
		String id = (String) session.getAttribute("loginID");
		dto.setWriter("test1");
		Service.personalContactTagInsert(dto);
		return "redirect:/contact/personal";
	}
	
	@RequestMapping("personalContactInsert")
	public String personalContactInsert(ContactDTO dto) throws Exception{
		String id = (String) session.getAttribute("loginId");
		dto.setWriter("test1");
		Service.personalContactInsert(dto);
		return "redirect:/contact/personal";
	}
	
	@ResponseBody
	@RequestMapping("personalContactInsertList")
	public void personalContactInsertList(ContactDTO dto) throws Exception{
		String id = (String) session.getAttribute("loginId");
		dto.setWriter("test1");
		Service.personalContactInsert(dto);
	}
	
	@ResponseBody
	@RequestMapping("personalContactRead")
	public List<ContactDTO> personalContactRead(ContactDTO dto) throws Exception{
		String id = (String) session.getAttribute("loginId");
		dto.setWriter("test1");
				List<ContactDTO> personalContactList = Service.personalContactRead(dto);
				return personalContactList;
	}
	
	@RequestMapping("personalContactUpdate")
	public String personalContactUpdate(ContactDTO dto) throws Exception{
		String id = (String) session.getAttribute("loginId");
		dto.setWriter("test1");
		Service.personalContactUpdate(dto);
		return "redirect:/contact/personal";
	}
	
	@RequestMapping("personalContactDelete")
	public String personalContactDelete(ContactDTO dto) throws Exception{
		String id = (String) session.getAttribute("loginId");
		dto.setWriter("test1");
		Service.personalContactDelete(dto);
		System.out.println(dto.getSeq());
		return "redirect:/contact/personal";
	}
	
	@RequestMapping("personalContactSearch")
	public String personalContactDelete(@RequestParam("keyword") String keyword, Model model) throws Exception {
		ContactDTO dto = new ContactDTO();
		
		String id = (String) session.getAttribute("loginId");
	    dto.setWriter("test1");
	    
	    System.out.println(keyword);
	    
	    List<ContactDTO> personalContactList = Service.personalContactSearch(keyword);
	    System.out.println(personalContactList.size());
    	model.addAttribute("personalContactList", personalContactList);
    	model.addAttribute("contactName", "검색된 항목");
    	
		List<ContactDTO> personalTagList = Service.personalContactTagSelectAll(dto);
		List<ContactDTO> shareTagList = Service.shareContactTagSelectAll(dto);
		
		model.addAttribute("personalTagList", personalTagList);
	    model.addAttribute("shareTagList", shareTagList);
	    
    	return "contact/personal";
	}
	
	@ResponseBody
	@RequestMapping("shareContactTagSelectAll")
	public List<ContactDTO> shareContactTagSelectAll(ContactDTO dto) {
		return this.Service.shareContactTagSelectAll(dto);
	}
	
	@ResponseBody
	@RequestMapping("shareContactTagSelectAllDeplicate")
	public List<ContactDTO> shareContactTagSelectAllDeplicate(ContactDTO dto) {
		String id = (String) session.getAttribute("loginID");
		dto.setWriter("test1");
		return Service.shareContactTagSelectAllDeplicate(dto);
	}
	
	@ResponseBody
	@RequestMapping("shareContactTagInsert")
	public int shareContactTagInsert(ContactDTO dto) {
		String id = (String) session.getAttribute("loginID");
		dto.setWriter("test1");
		return Service.shareContactTagInsert(dto);
	}
	
	@RequestMapping("shareContactInsert")
	public String shareContactInsert(ContactDTO dto) throws Exception{
		String id = (String) session.getAttribute("loginId");
		dto.setWriter("test1");
		Service.shareContactInsert(dto);
		return "redirect:/contact/share";
	}
	
	@ResponseBody
	@RequestMapping("shareContactInsertList")
	public void shareContactInsertList(ContactDTO dto) throws Exception{
		String id = (String) session.getAttribute("loginId");
		dto.setWriter("test1");
		Service.shareContactInsert(dto);
	}
	
}
