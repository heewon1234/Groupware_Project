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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdt.dto.LeavesDTO;
import com.kdt.dto.MembersDTO;
import com.kdt.dto.Official_CalendarDTO;
import com.kdt.dto.OneToOneChatDTO;
import com.kdt.dto.WorksDTO;
import com.kdt.services.CalendarService;
import com.kdt.services.ChatRoomService;
import com.kdt.services.MembersService;
import com.kdt.services.WorksService;
import com.kdt.utils.EncryptionUtils;
import com.kdt.utils.UUIDToNumber;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/members/")
public class MembersController {
	@Autowired
	private MembersService mservice;

	@Autowired
	private HttpSession session;

	@Autowired
	private ChatRoomService roomService;

	@Autowired
	private CalendarService cservice;

	@Autowired
	private WorksService wservice;

	@RequestMapping("login")
	public String login(String id, String pw, Model model) throws Exception{
		String shapw = EncryptionUtils.getSHA512(pw);
		boolean result = this.mservice.isMember(id,shapw);
		if(result) {
			session.setAttribute("loginId",id);
			MembersDTO userDTO = mservice.loginUser(id);
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

			String org = this.mservice.getOrganization(id);
			List<Official_CalendarDTO> list = this.cservice.selectAllO(org);

			//ObjectMapper objectMapper = new ObjectMapper();
			//String listAsJSON = objectMapper.writeValueAsString(list);

			model.addAttribute("list",list);
			return "redirect:/";
		} else {
			boolean login = false;
			model.addAttribute("login",login);
		}
		return "home";
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
		String shapw = EncryptionUtils.getSHA512(pw);
		MembersDTO dto = new MembersDTO(id,shapw,name,workForm,org,jobName,position,null,null,null,null,null,null);
		LeavesDTO ldto = new LeavesDTO(id,name,org,null,0,0,0,0,0,0,0);
		WorksDTO wdto = new WorksDTO(id,name,org,0,0,0,0,0,0,0,0);
		this.wservice.leave_insert(ldto);
		this.wservice.work_insert(wdto);
		this.mservice.signup(dto);
		return "redirect:/insa/manage/members";
	}

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
		wservice.leave_update(idList, org);
		wservice.work_update(idList, org);
		return "redirect:/insa/manage/members";
	}

	@RequestMapping(value = "/deleteMember")
	public String deleteMember(String idList) throws Exception {
		mservice.deleteMember(idList);
		wservice.leave_delete(idList);
		wservice.work_delete(idList);
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
			System.out.println(member);
			userIDs.add(member.getId());
		}
		System.out.println(userIDs);

		// 1:1 채팅방을 생성하고 이미 방이 없는 경우에만 생성
		for (String otherUserID : userIDs) {
			// 이미 방이 있는지 확인
			if (!roomService.oneroomExists(loggedInUserID, otherUserID)) {
				System.out.println(roomService.oneroomExists(loggedInUserID, otherUserID));
				int oneSeq = UUIDToNumber.convertUUIDToPositiveInt();
				roomService.createOneChatRoom(loggedInUserID, otherUserID,oneSeq);
				// 나머지 로직 추가 가능
			}
		}

		List<OneToOneChatDTO> OneToOneChatDTOList = roomService.selectAll(loggedInUserID);
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

		List<OneToOneChatDTO> OneToOneChatDTOList = roomService.selectAll(id);
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("members", members);
		responseData.put("OneToOneChatDTOList", OneToOneChatDTOList);
		return responseData;
	}
	@RequestMapping("getUserList")
	@ResponseBody
	public List<String> getUserList() throws Exception {
		List<MembersDTO> membersList = mservice.getUserList();
		List<String> memberNames = new ArrayList<>();
		for (MembersDTO member : membersList) {
			String memberName = member.getName();
			memberNames.add(memberName);
		}
		return memberNames;
	}
	@RequestMapping("getDepartmentList")
	@ResponseBody
	public List<String> getDepartmentList() throws Exception {
		System.out.println(mservice.getDepartmentList());
		return mservice.getDepartmentList();
	}
	@RequestMapping("countUser")
	@ResponseBody
	public int countUser() throws Exception {
		return mservice.countUser();
	}
	@RequestMapping("selectAll")
	@ResponseBody
	public List<MembersDTO> selectAll() throws Exception {
		return mservice.selectAll();
	}

	@RequestMapping("/myInfo")
	public String myInfo(Model model) {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		userDTO = mservice.loginUser(userDTO.getId());
		model.addAttribute("userDTO", userDTO);
		return "/members/myInfo";
	}

	@RequestMapping("/left_item")
	public String leftItem() {
		return "/members/left_item";
	}

	@RequestMapping("/updateMember")
	public String updateMember(String pw, String zipcode, String phone, String email, String address1, String address2, String profile_image) {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		String shapw;

		if(pw=="") {
			shapw = userDTO.getPw();
		} else {
			shapw = EncryptionUtils.getSHA512(pw);
		}

		System.out.println(profile_image);

		MembersDTO dto = new MembersDTO(userDTO.getId(), shapw, null, null, null, null, null, phone, email, zipcode, address1, address2, profile_image);

		mservice.updateMember(dto);

		return "redirect:/members/myInfo";
	}
}
