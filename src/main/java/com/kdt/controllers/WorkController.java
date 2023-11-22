package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.dto.LeavesDTO;
import com.kdt.dto.MembersDTO;
import com.kdt.dto.WorkPlanDTO;
import com.kdt.dto.WorkTimesDTO;
import com.kdt.dto.WorksDTO;
import com.kdt.dto.WorkstatisticsDTO;
import com.kdt.services.JobTitleService;
import com.kdt.services.MembersService;
import com.kdt.services.OrganizationService;
import com.kdt.services.WorksService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/works/")
public class WorkController {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private WorksService wservice;
	@Autowired
	private MembersService mservice;
	@Autowired
	private OrganizationService orgService;
	@Autowired
	private JobTitleService jobService;
	
	
	@RequestMapping("work_leave")
	public String towork_leave(Model model)throws Exception {
		String ID = (String) session.getAttribute("loginId");
		List<WorksDTO> list = wservice.select(ID);
		model.addAttribute("list",list);
		return "/insa/mywork/work_leave";
	}
	@RequestMapping("workmanage")
	public String toworkmanage(Model model)throws Exception {
		List<LeavesDTO> list = wservice.leavenull();
		model.addAttribute("list",list);
		return "/insa/work_manage/workmanage";
	}
	@RequestMapping("workstatistics")
	public String toworkstatistics(Model model)throws Exception {
		return "/insa/work_manage/workstatistics";
	}
	@ResponseBody
	@RequestMapping("insert") //출근시간 퇴근시간 삽입
	public List<WorkTimesDTO> insert(WorkTimesDTO dto,Model model)throws Exception {
		String ID = (String) session.getAttribute("loginId");
		dto.setId(ID);
		int today = wservice.existstoday(ID);
		System.out.println(today);
		if(today==0 && dto.getWork_type().equals("근무중")) { // 09시 이후 출근 클릭
			wservice.insert(dto);
			wservice.addworklate(ID); // 지각 추가
		List<WorkTimesDTO> tlist = wservice.selectby(ID);
		model.addAttribute("tlist",tlist);
		return tlist;
		}
		else if(today==0 && dto.getWork_type().equals("근무 종료")) { // 18시 이전 퇴근 클릭
			wservice.insert(dto);
			wservice.addworkearly(ID); // 조기 퇴근 추가
			wservice.addworkday(ID); // 근무일 수 추가
			wservice.addworkminutetime(ID); // 근무시간 추가
			wservice.addworknotcheck(ID); // 퇴근 미체크 추가
		List<WorkTimesDTO> tlist = wservice.selectby(ID);
		model.addAttribute("tlist",tlist);
		return tlist;
		}
		else {
			wservice.insert(dto);
			List<WorkTimesDTO> tlist = wservice.selectby(ID);
			model.addAttribute("tlist",tlist);
			return tlist;
		}
		
	}
	@ResponseBody
	@RequestMapping("list") // 근무현황판 출력
	public List<WorkTimesDTO> list(Model model)throws Exception  {
		String ID = (String) session.getAttribute("loginId");
		List<WorkTimesDTO> tlist = wservice.selectby(ID);
		model.addAttribute("tlist",tlist);
		return tlist;
	}
	@ResponseBody
	@RequestMapping("workplanlist") // 근무현황판 출력
	public String workplanlist(Model model)throws Exception  {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		return wservice.selectbyname(userDTO.getName());
	}
	
	@ResponseBody
	@RequestMapping("work_inout") // 출근시간 퇴근시간 시간 출력
	public List<WorkTimesDTO> work_inout(Model model)throws Exception  {
		String ID = (String) session.getAttribute("loginId");
		List<WorkTimesDTO> list = wservice.work_inout(ID);
		model.addAttribute("list",list);
		return list;
	}
	/*@RequestMapping("left_item")
	public String toLeft_item(String selectItem, Model model) {
		model.addAttribute("selectItem", selectItem);
		return "/insa/left_item";
	}*/
	/*@RequestMapping("manage_left_item")
	public String tomanage_Left_item(String selectItem, Model model) {
		model.addAttribute("selectItem", selectItem);
		return "/insa/work_manage/left_item";
	}*/
	@ResponseBody
	@RequestMapping("statisticslist") // 근무통계 출력
	public List<WorkstatisticsDTO> statisticslist(Model model)throws Exception  {
		List<WorkstatisticsDTO> list = wservice.statisticslist();
		model.addAttribute("list",list);
		return list;
	}

	@ResponseBody
	@RequestMapping("workmanagelist") // 근무통계 출력
	public List<LeavesDTO> workmanage(Model model)throws Exception  {
		List<LeavesDTO> list = wservice.leaveslist();
		model.addAttribute("list",list);
		return list;
	}
	@RequestMapping("joindayupdate")
	public String joindayupdate(String idList,String joindayList) throws Exception {
		System.out.println(idList);
		System.out.println(joindayList);
	    wservice.leave_joindayupdate(idList,joindayList);
	    wservice.updateLeaveRemainder(idList,joindayList);
	    return "redirect:/works/workmanage";
	}
	@RequestMapping("leave_apply")
	public String toleave_apply(Model model)throws Exception {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		String ID = (String) session.getAttribute("loginId");
		List<WorksDTO> list = wservice.select(ID);
		List<String> managerOrgList = orgService.getManagerOrgList(userDTO.getOrganization());
		List<String> managerPositionList = jobService.getManagerPosition(userDTO.getPosition());
		List<MembersDTO> managerList = mservice.getManagerList(managerOrgList, managerPositionList);
		model.addAttribute("list", list);
		model.addAttribute("userDTO", userDTO);
		model.addAttribute("managerList", managerList);
		return "/insa/mywork/leave_apply";
	}
	
	@RequestMapping("getUserList")
	@ResponseBody
	public List<MembersDTO> getUserList(Model model)throws Exception {
		return mservice.getUserList();
	}
	@RequestMapping("work_current")
	   public String work_current(Model model)throws Exception {
	      String name = (String) session.getAttribute("name");
	      List<WorkPlanDTO> WorkPlanList = wservice.work_current_selectByName(name);
	      model.addAttribute("WorkPlanList", WorkPlanList);
	      return "/insa/mywork/work_current";
	   }
	

}
