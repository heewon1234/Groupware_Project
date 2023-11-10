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
	
	@MessageMapping("chat/message")
	@SendTo("/topic/chat")
	public ChatMessageDTO message(ChatMessageDTO message) {
		return message;
	}

//	@MessageMapping("/oneToOne/sendMessage/{oneSeq}")
//	@SendTo("/topic/oneToOne/{oneSeq}")
//	public ChatMessageDTO oneToOne_insert(@Payload ChatMessageDTO message, @DestinationVariable int oneSeq) throws Exception {
//	    System.out.println("메세지"+message);
//	    //ChatMessageDTO dto = gson.fromJson(message, new TypeToken<ChatMessageDTO>() {}.getType());
//	    System.out.println("확인" + oneSeq);
//	    //System.out.println(dto.getRoomID());
//	    chatMessageService.insert(message);
//	    return message;
//	}
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




//	@MessageMapping("/group/sendMessage")
//	@SendTo("/topic/group/{groupId}")
//	public int group_insert(ChatMessageDTO group_dto) throws Exception {
//		return chatMessageService.insert(group_dto);
//	}
	@MessageMapping("/group/sendMessage")
	@SendTo("/topic/group/{groupId}")
	public void group_insert(@Payload String roomInfo) throws Exception {
		GroupChatDTO group_dto = gson.fromJson(roomInfo, new TypeToken<GroupChatDTO>() {}.getType());
		ChatRoomService.createGroupChat(group_dto);
	}
//	   @MessageMapping("/group/sendMessage")
//	   @SendTo("/topic/group/{groupId}")
//	   public void group_insert(@Payload String roomInfo) throws Exception {
//	       // JSON 파싱
//	       JsonObject jsonObject = JsonParser.parseString(roomInfo).getAsJsonObject();
//	       String groupName = jsonObject.get("groupName").getAsString();
//	       String memberName = jsonObject.get("memberName").getAsString();
//	       ChatRoomService.createGroupChat(groupName)
//	       System.out.println("groupName: " + groupName);
//	       System.out.println("memberName: " + memberName);
//
//	       // 이제 groupName 및 memberName을 각각 사용하여 원하는 작업을 수행할 수 있습니다.
//	   }

	@RequestMapping(value = "/chat/selectByType", method = RequestMethod.GET)
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
	}



}
