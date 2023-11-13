package com.kdt.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kdt.dto.GroupChatDTO;
import com.kdt.dto.MembersDTO;
import com.kdt.dto.OneToOneChatDTO;
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

	@RequestMapping("InvitedGroups")
	@ResponseBody
	public List<GroupChatDTO> groupSelectInvited() throws Exception {
		String name = (String) session.getAttribute("name");
		System.out.println(name);
	    List<GroupChatDTO> allGroups = service.groupSelectAll();
	    List<GroupChatDTO> invitedGroups = new ArrayList<>();

	    for (GroupChatDTO group : allGroups) {
	        if (group.getMemberName().contains(name)) {
	            invitedGroups.add(group);
	        }
	    }
	    return invitedGroups;
	}

	
	@RequestMapping(value = "createGroupChat", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String createGroupChat(@RequestBody GroupChatDTO group_dto) throws Exception {
		service.createGroupChat(group_dto);
		return "Success"; 
	}
	//그룹채팅방 검색
	@RequestMapping("searchGroup")
	@ResponseBody
	public List<GroupChatDTO> searchGroup(@RequestParam String groupValue) throws Exception{
		String name = (String)session.getAttribute("name");
		return service.searchGroup(groupValue,name);
	}
}
