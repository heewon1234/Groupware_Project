package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kdt.dto.ChatMessageDTO;
import com.kdt.dto.MembersDTO;
import com.kdt.services.ChatMessageService;
import com.kdt.services.ChatRoomService;
import com.kdt.services.MembersService;

@Controller
public class ChatStompController {
	@Autowired
	private ChatMessageService chatMessageService;
	@Autowired
	private ChatRoomService ChatRoomService;
	@Autowired
	private MembersService mservice;
	@Autowired
	private Gson gson;
	
	@MessageMapping("/oneToOne/sendMessage/{oneSeq}")
	@SendTo("/topic/oneToOne/{oneSeq}")
	public ChatMessageDTO oneToOne_insert(@Payload ChatMessageDTO message, @DestinationVariable int oneSeq) throws Exception {
	    chatMessageService.insert(message);
	    return message;
	}
	

	@ResponseBody
	@RequestMapping("/getPreviousMessages/{oneSeq}")//login된 userID를 where절로 줘야함 이름임
	public List<ChatMessageDTO> getPreviousMessages(@PathVariable String oneSeq) {
		//프로필 업데이트
		List<MembersDTO> updateProfile = mservice.selectAll();
		chatMessageService.updateProfile(updateProfile);
	    List<ChatMessageDTO> previousMessages = chatMessageService.getPreviousMessages(oneSeq);
	    return previousMessages;
	}
	@ResponseBody
	@RequestMapping("/getPreviousGroupMessages/{groupSeq}")//login된 userID를 where절로 줘야함 이름임
	public List<ChatMessageDTO> getPreviousGroupMessages(@PathVariable String groupSeq) {
		List<MembersDTO> updateProfile = mservice.selectAll();
		chatMessageService.updateProfile(updateProfile);
		List<ChatMessageDTO> previousGroupMessages = chatMessageService.getPreviousGroupMessages(groupSeq);
		return previousGroupMessages;
	}


	@MessageMapping("/group/sendMessage/{groupSeq}")
	@SendTo("/topic/group/{groupSeq}")
	public ChatMessageDTO group_insert(@Payload ChatMessageDTO message, @DestinationVariable int groupSeq) throws Exception {
		chatMessageService.insert(message);
		return message;
	}
}
