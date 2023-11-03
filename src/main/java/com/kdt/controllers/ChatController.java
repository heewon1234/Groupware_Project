package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/chats/")
public class ChatController {
	@Autowired
	private HttpSession hsession;
	
	@RequestMapping("/chatting")
	public String chatting(HttpServletRequest request) {
		String id = (String)hsession.getAttribute("loginID");
		hsession.setAttribute("id", id);
		return "chat/chatting";
	}
	@RequestMapping("/inputText")
	public String inputText() {
		return "chat/inputText";
	}
	@RequestMapping("/chatList")
	public String chatList() {
		return "chat/chatList";
	}
	@RequestMapping("/groupchatList")
	public String groupchatList() {
		return "chat/gchatList";
	}
	@RequestMapping("/chatMembers")
	public String chatMembers() {
		return "chat/chatMembers";
	}
	@RequestMapping("/messenger")
	public String messenger() {
		return "chat/messenger";
	}
}
