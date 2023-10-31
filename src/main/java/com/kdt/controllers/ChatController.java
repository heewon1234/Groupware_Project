package com.kdt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chats/")
public class ChatController {
	
	@RequestMapping("/chatting")
	public String chatting() {
		return "chat/chatting";
	}
	@RequestMapping("/inputText")
	public String inputText() {
		return "chat/inputText";
	}
	@RequestMapping("/messenger")
	public String messenger() {
		return "chat/messenger";
	}
}
