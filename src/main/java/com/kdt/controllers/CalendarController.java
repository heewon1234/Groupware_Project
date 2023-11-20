package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdt.dto.MembersDTO;
import com.kdt.dto.Official_CalendarDTO;
import com.kdt.dto.Personal_CalendarDTO;
import com.kdt.services.CalendarService;
import com.kdt.services.JobTitleService;
import com.kdt.services.MembersService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/calendar/")
public class CalendarController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private CalendarService cservice;
	
	@Autowired
	private MembersService mservice;
	
	@Autowired
	private JobTitleService jtService;
	
	@RequestMapping("official")
	public String ocalendar(Model model) throws Exception{
		String id = (String) session.getAttribute("loginId");
		
		MembersDTO userDTO = mservice.loginUser(id);
		String position = userDTO.getPosition();
		int rank = jtService.getRank(position);
		
		String org = this.mservice.getOrganization(id);
		List<Official_CalendarDTO> list = this.cservice.selectAllO(org);
		
		ObjectMapper objectMapper = new ObjectMapper();
        String listAsJSON = objectMapper.writeValueAsString(list);
        
        model.addAttribute("rank",rank);
		model.addAttribute("list",listAsJSON);
		return "calendar/official";
	}
	
	@RequestMapping("personal")
	public String pcalendar(Model model) throws Exception{
		String id = (String) session.getAttribute("loginId");
		List<Personal_CalendarDTO> list = this.cservice.selectAllP(id);
		
		ObjectMapper objectMapper = new ObjectMapper();
        String listAsJSON = objectMapper.writeValueAsString(list);
        
		model.addAttribute("list",listAsJSON);
		return "calendar/personal";
	}
	
	@RequestMapping("pcreate")
	public String pupload(String calendar_content, String calendar_start_date, String calendar_end_date) throws Exception{
		String id = (String) session.getAttribute("loginId");
		this.cservice.pinsert(new Personal_CalendarDTO(0,id,calendar_content,calendar_start_date,calendar_end_date));
		
		return "redirect:/calendar/personal";
	}
	
	@RequestMapping("ocreate")
	public String oupload(String calendar_content, String calendar_start_date, String calendar_end_date) throws Exception{
		String id = (String) session.getAttribute("loginId");
		String org = this.mservice.getOrganization(id);
		this.cservice.oinsert(new Official_CalendarDTO(0,org,calendar_content,calendar_start_date,calendar_end_date));
		
		return "redirect:/calendar/official";
	}
	
	@ResponseBody
	@RequestMapping("pdelete")
    public String pdelete(int seq){
		int result = this.cservice.pdelete(seq);
        return String.valueOf(result);
    }
	
	@ResponseBody
	@RequestMapping("odelete")
    public String odelete(int seq){
		int result = this.cservice.odelete(seq);
        return String.valueOf(result);
    }
	
	@ResponseBody
	@RequestMapping("pupdate")
    public String pupdate(int seq, String title, String start, String end){
		String id = (String) session.getAttribute("loginId");
		int result = this.cservice.pupdate(new Personal_CalendarDTO(seq,id,title,start,end));
        return String.valueOf(result);
    }
	
	@ResponseBody
	@RequestMapping("oupdate")
    public String oupdate(int seq, String title, String start, String end){
		String id = (String) session.getAttribute("loginId");
		String org = this.mservice.getOrganization(id);
		int result = this.cservice.oupdate(new Official_CalendarDTO(seq,org,title,start,end));
        return String.valueOf(result);
    }
}
