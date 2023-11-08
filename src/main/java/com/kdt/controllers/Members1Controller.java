package com.kdt.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.MembersDTO1;
import com.kdt.dto.OneToOneChatDTO;
import com.kdt.services.ChatRoomService;
import com.kdt.services.MembersService1;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/members1/")
public class Members1Controller {
	@Autowired
	private MembersService1 mservice;
	@Autowired
	private ChatRoomService roomService;
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	@Autowired
	private HttpSession session;
	public String generateUniqueUUID() {
	    UUID uuid = UUID.randomUUID();
	    return uuid.toString();
	}

	@RequestMapping("selectAll")
	@ResponseBody
	public Map<String, Object> selectAll(HttpSession session, Model model) throws Exception {
		String loggedInUserID = (String) session.getAttribute("loginId");
		List<MembersDTO1> list = mservice.selectAll(loggedInUserID);

		// 사용자 목록에서 ID를 추출
		List<String> userIDs = new ArrayList<>();
		for (MembersDTO1 member : list) {
			userIDs.add(member.getId());
		}
		System.out.println(userIDs);

		// 1:1 채팅방을 생성하고 이미 방이 없는 경우에만 생성
		for (String otherUserID : userIDs) {
		    // 이미 방이 있는지 확인
		    if (!roomService.oneroomExists(loggedInUserID, otherUserID)) {
		        System.out.println(roomService.oneroomExists(loggedInUserID, otherUserID));
		        String oneSeq = generateUniqueUUID();
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
	public Map<String, Object> getMembersByOrganization(@RequestParam("organization") String organization,@RequestParam("oneSeq") int oneSeq) throws Exception {
		String id = (String) session.getAttribute("loginId");
		List<MembersDTO1> members = mservice.getMembersByOrganization(organization,id);

		List<OneToOneChatDTO> OneToOneChatDTOList = roomService.selectAll();
		System.out.println(OneToOneChatDTOList);
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("members", members);
		responseData.put("OneToOneChatDTOList", OneToOneChatDTOList);
		return responseData;
	}

	@PostMapping("/loginUser")
	@ResponseBody
	public void loginUser(HttpSession session) {
		String loginId = (String) session.getAttribute("loginId");

		// mservice.loginUser(loginId)를 통해 로그인된 사용자의 DTO 가져오기
		MembersDTO1 userDTO = mservice.loginUser(loginId);
		System.out.println(userDTO);
		if (userDTO != null) {
			// DTO에서 이름과 부서 가져오기
			String name = userDTO.getName();
			String position = userDTO.getPosition();

			// 세션에 이름과 부서 정보 저장
			session.setAttribute("name", name);
			session.setAttribute("position", position);

		}
	}




}
