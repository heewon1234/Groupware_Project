package com.kdt.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.kdt.dto.ChatMessageDTO;

@Controller
public class ChatStompController {
	
	@MessageMapping("chat/message")
    @SendTo("/topic/chat")
    public ChatMessageDTO message(ChatMessageDTO message) {
        return message;
    }
}
