package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kdt.dto.GroupChatDTO;
import com.kdt.services.ChatRoomService;

@Controller
@RequestMapping("/groupChatRooms/")
public class ChatGroupRoomController {

	@Autowired
	private ChatRoomService service;

	@Autowired
	private Gson gson;

	@RequestMapping("groupSelectAll")
	@ResponseBody
	public List<GroupChatDTO> groupSelectAll() throws Exception {
		return service.groupSelectAll();
	}
	@RequestMapping(value = "createGroupChat", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String createGroupChat(@RequestBody GroupChatDTO group_dto) throws Exception {
	    System.out.println("그룹" + group_dto.getGroupName());
	    System.out.println("멤버" + group_dto.getMemberName());
	    service.createGroupChat(group_dto);
	    return "Success"; 
	}

}
