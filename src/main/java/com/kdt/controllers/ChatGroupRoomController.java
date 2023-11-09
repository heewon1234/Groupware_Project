package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.dto.GroupChatDTO;
import com.kdt.services.ChatRoomService;

@Controller
@RequestMapping("/groupChatRooms/")
public class ChatGroupRoomController {

	@Autowired
	private ChatRoomService service;


	@RequestMapping("groupSelectAll")
	@ResponseBody
	public List<GroupChatDTO> groupSelectAll() throws Exception {
		return service.groupSelectAll();
	}



}
