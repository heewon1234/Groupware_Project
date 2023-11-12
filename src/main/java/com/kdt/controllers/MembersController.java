package com.kdt.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.dto.MembersDTO;
import com.kdt.dto.MembersDTO1;
import com.kdt.dto.OneToOneChatDTO;
import com.kdt.services.ChatRoomService;
import com.kdt.services.MembersService;
import com.kdt.services.MembersService1;
import com.kdt.utils.UUIDToNumber;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/members/")
public class MembersController {
	@Autowired
	private MembersService mservice;
	
	@Autowired
	private MembersService1 mservice1;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ChatRoomService roomService;
	
	@RequestMapping("login")
	public String login(String id, String pw) throws Exception{
		//String shapw = EncryptionUtils.getSHA512(pw);
		boolean result = this.mservice.isMember(id,pw);
		if(result) {
			session.setAttribute("loginId",id);
			MembersDTO1 userDTO = mservice1.loginUser(id);
		    System.out.println(userDTO);
		    if (userDTO != null) {
		        // DTO에서 이름과 부서 가져오기
		        String name = userDTO.getName();
		        String position = userDTO.getPosition();

		        // 세션에 이름과 부서 정보 저장
		        session.setAttribute("name", name);
		        session.setAttribute("position", position);
		        
		        // 이름과 부서 따로 저장하지 말고
		        // 이걸로 쓰면 편할거 같아요
		        session.setAttribute("userDTO", userDTO);

		    }
			return "home";
		}
		
		
		return "redirect:/";
	}
	
	@RequestMapping("logout")
	public String logout() throws Exception{
		session.invalidate();
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping(value="idCheck",produces="text/plain;charset=utf8")
	public String idCheck(String id) throws Exception{
		boolean result = this.mservice.idCheck(id);
		return String.valueOf(result);
	}
	
	@RequestMapping("signup")
	public String signup(String name, String id, String pw, String workForm, String org, String position, String jobName) throws Exception{
		MembersDTO dto = new MembersDTO(id,pw,name,workForm,org,jobName,position,null,null,null,null,null,null);
		this.mservice.signup(dto);
		return "redirect:/insa/manage/members";
	}
	
	//
	//
	//
	@RequestMapping(value = "/updateWorkForm")
	public String updateWorkForm(String idList, String workForm) throws Exception {
		mservice.updateWorkForm(idList, workForm);

		return "redirect:/insa/manage/members";
	}
	
	@RequestMapping(value = "/updatePosition")
	public String updatePosition(String idList, String position) throws Exception {
		mservice.updatePosition(idList, position);

		return "redirect:/insa/manage/members";
	}
	
	@RequestMapping(value = "/updateOrg")
	public String updateOrg(String idList, String org) throws Exception {
		mservice.updateOrg(idList, org);

		return "redirect:/insa/manage/members";
	}
	
	@RequestMapping(value = "/deleteMember")
	public String deleteMember(String idList) throws Exception {
		mservice.deleteMember(idList);

		return "redirect:/insa/manage/members";
	}
	//채팅 관련 기능입니다.
	@RequestMapping("selectAllNotLogged")
	@ResponseBody
	public Map<String, Object> selectAll(HttpSession session, Model model) throws Exception {
		String loggedInUserID = (String) session.getAttribute("loginId");
		List<MembersDTO> list = mservice.selectAllNotLogged(loggedInUserID);

		// 사용자 목록에서 ID를 추출
		List<String> userIDs = new ArrayList<>();
		for (MembersDTO member : list) {
			userIDs.add(member.getId());
		}
		System.out.println(userIDs);

		// 1:1 채팅방을 생성하고 이미 방이 없는 경우에만 생성
		for (String otherUserID : userIDs) {
		    // 이미 방이 있는지 확인
		    if (!roomService.oneroomExists(loggedInUserID, otherUserID)) {
		        System.out.println(roomService.oneroomExists(loggedInUserID, otherUserID));
		        int oneSeq = UUIDToNumber.convertUUIDToPositiveInt();
		        System.out.println(oneSeq);
		        roomService.createOneChatRoom(loggedInUserID, otherUserID,oneSeq);
		        // 나머지 로직 추가 가능
		    }
		}

		List<OneToOneChatDTO> OneToOneChatDTOList = roomService.selectAll();
		System.out.println(OneToOneChatDTOList);
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("list", list);
		responseData.put("OneToOneChatDTOList", OneToOneChatDTOList);


		return responseData;
	}
	@PostMapping("/getMembersByOrganization")
	@ResponseBody
	public Map<String, Object> getMembersByOrganization(@RequestParam("organization") String organization,@RequestParam("oneSeq") String oneSeq) throws Exception {
		String id = (String) session.getAttribute("loginId");
		List<MembersDTO> members = mservice.getMembersByOrganization(organization,id);

		List<OneToOneChatDTO> OneToOneChatDTOList = roomService.selectAll();
		System.out.println(OneToOneChatDTOList);
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("members", members);
		responseData.put("OneToOneChatDTOList", OneToOneChatDTOList);
		return responseData;
	}
}
