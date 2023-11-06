package com.kdt.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			if (!loggedInUserID.equals(otherUserID)) {
				// 이미 방이 있는지 확인
				if (!roomService.oneroomExists(loggedInUserID, otherUserID)) {
					System.out.println(roomService.oneroomExists(loggedInUserID, otherUserID));
					roomService.createOneChatRoom(loggedInUserID, otherUserID);
					//	                // 방 ID를 기반으로 채팅방 입장 메시지 전송
					//	                String destination = "/topic/oneToOne/" + oneSeq;
					//	                String message = "User " + loggedInUserID + " has entered the chat with " + otherUserID + ".";
					//	                messagingTemplate.convertAndSend(destination, message);
				}
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
	public List<MembersDTO1> getMembersByOrganization(@RequestParam("organization") String organization) {
		List<MembersDTO1> members = mservice.getMembersByOrganization(organization);
		return members;
	}


}
