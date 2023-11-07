package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.services.ChatRoomService;

@Controller
@RequestMapping("/groupChatRooms/")
public class ChatGroupRoomController {
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
    private ChatRoomService service;

    @RequestMapping("createGroupChat")
    @ResponseBody
    public String createGroupChat(@RequestParam("groupName") String groupName, @RequestParam("users") List<String> users) {
        // 그룹 채팅방 생성 로직
        int groupSeq = service.createGroupChat(groupName, users);
        
        if (groupSeq > 0) {
            return "Group chat room created with ID: " + groupSeq;
        } else {
            return "Failed to create the group chat room.";
        }
    }

    @RequestMapping("addUserToGroupChat")
    @ResponseBody
    public String addUserToGroupChat(@RequestParam("groupSeq") int groupSeq, @RequestParam("user") String user) {
        // 사용자를 그룹 채팅방에 추가하는 로직
        boolean added = service.addUserToGroupChat(groupSeq, user);

        if (added) {
            return user + " added to the group chat room.";
        } else {
            return "Failed to add " + user + " to the group chat room.";
        }
    }

	
	
}
