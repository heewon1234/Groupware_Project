package com.kdt.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kdt.dto.ChatMessageDTO;
import com.kdt.dto.GroupChatDTO;
import com.kdt.services.ChatMessageService;
import com.kdt.services.ChatRoomService;

@Controller
public class ChatStompController {
	@Autowired
	private ChatMessageService chatMessageService;
	@Autowired
	private ChatRoomService ChatRoomService;

	@Autowired
	private Gson gson;
	
	@MessageMapping("/oneToOne/sendMessage/{oneSeq}")
	@SendTo("/topic/oneToOne/{oneSeq}")
	public ChatMessageDTO oneToOne_insert(@Payload ChatMessageDTO message, @DestinationVariable int oneSeq) throws Exception {
	    System.out.println("메세지" + message.getUserID());
	    chatMessageService.insert(message);
	    return message;
	}

	@ResponseBody
	@RequestMapping("/getPreviousMessages/{oneSeq}")//login된 userID를 where절로 줘야함 이름임
	public List<ChatMessageDTO> getPreviousMessages(@PathVariable String oneSeq) {
		System.out.println("이전" +oneSeq);
	    List<ChatMessageDTO> previousMessages = chatMessageService.getPreviousMessages(oneSeq);
	    return previousMessages;
	}


	@MessageMapping("/group/sendMessage")
	@SendTo("/topic/group/{groupSeq}")
	public void group_insert(@Payload String roomInfo) throws Exception {
		GroupChatDTO group_dto = gson.fromJson(roomInfo, new TypeToken<GroupChatDTO>() {}.getType());
		ChatRoomService.createGroupChat(group_dto);
	}

	/*@RequestMapping(value = "/chat/selectByType", method = RequestMethod.GET)
	public List<ChatMessageDTO> selectChat(@RequestParam("type") String type, @RequestParam("MessageSeq") int MessageSeq) {
		List<ChatMessageDTO> dto = new ArrayList<>();

		if ("oneToOne".equals(type)) {
			// 일대일 채팅을 가져오는 로직
			Map<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("type", "oneToOne");
			parameterMap.put("MessageSeq", MessageSeq);
			dto = chatMessageService.selectByType(parameterMap);
		} else if ("group".equals(type)) {
			// 그룹 채팅을 가져오는 로직
			Map<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("type", "group");
			parameterMap.put("MessageSeq", MessageSeq);
			dto = chatMessageService.selectByType(parameterMap);
		}

		// 나머지 로직
		return dto;
	}*/



}
