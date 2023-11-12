package com.kdt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kdt.dto.GroupChatDTO;
import com.kdt.services.ChatRoomService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/groupChatRooms/")
public class ChatGroupRoomController {

	@Autowired
	private ChatRoomService service;

	@Autowired
	private Gson gson;
	
	@Autowired
	private HttpSession session;

	/*@RequestMapping("groupSelectAll")
	@ResponseBody
	public List<GroupChatDTO> groupSelectAll() throws Exception {
		return service.groupSelectAll();
	}*/
	
	@RequestMapping("InvitedGroups")
	@ResponseBody
	public List<GroupChatDTO> groupSelectInvited() throws Exception {
		String name = (String) session.getAttribute("name");
		System.out.println(name);
	    // TODO: userId를 기반으로 초대된 방 정보만 필터링하여 가져오는 로직 작성
	    // 예시: groupSelectAll()에서 가져온 방 목록을 userId를 이용하여 필터링
	    List<GroupChatDTO> allGroups = service.groupSelectAll();
	    List<GroupChatDTO> invitedGroups = new ArrayList<>();

	    for (GroupChatDTO group : allGroups) {
	        if (group.getMemberName().contains(name)) {
	            invitedGroups.add(group);
	        }
	    }
	    System.out.println("그룹채팅방"+invitedGroups);
	    return invitedGroups;
	}

	
	@RequestMapping(value = "createGroupChat", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String createGroupChat(@RequestBody GroupChatDTO group_dto) throws Exception {
		System.out.println("그룹" + group_dto.getGroupName());
		System.out.println("멤버" + group_dto.getMemberName());
		service.createGroupChat(group_dto);
		return "Success"; 
	}
	//그룹채팅방 검색
	@RequestMapping("searchGroup")
	@ResponseBody
	public List<GroupChatDTO> searchGroup(@RequestParam String groupValue) throws Exception{
		return service.searchGroup(groupValue);
	}
}
