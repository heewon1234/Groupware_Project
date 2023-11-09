package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdt.dto.Personal_CalendarDTO;
import com.kdt.services.CalendarService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/calendar/")
public class CalendarController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private CalendarService cservice;
	
	@RequestMapping("personal")
	public String calendar(Model model) throws Exception{
		String id = (String) session.getAttribute("loginId");
		List<Personal_CalendarDTO> list = this.cservice.selectAll(id);
		
		ObjectMapper objectMapper = new ObjectMapper();
        String listAsJSON = objectMapper.writeValueAsString(list);
        
		model.addAttribute("list",listAsJSON);
		return "calendar/personal";
	}
	
	@RequestMapping("create")
	public String upload(String calendar_content, String calendar_start_date, String calendar_end_date) throws Exception{
		String id = (String) session.getAttribute("loginId");
		this.cservice.insert(new Personal_CalendarDTO(0,id,calendar_content,calendar_start_date,calendar_end_date));
		
		return "redirect:/calendar/personal";
	}
	
	@ResponseBody
	@RequestMapping("delete")
    public String delete(int seq){
		int result = this.cservice.delete(seq);
        return String.valueOf(result);
    }
	
	@ResponseBody
	@RequestMapping("update")
    public String update(int seq, String title, String start, String end){
		String id = (String) session.getAttribute("loginId");
		int result = this.cservice.update(new Personal_CalendarDTO(seq,id,title,start,end));
        return String.valueOf(result);
    }
}
