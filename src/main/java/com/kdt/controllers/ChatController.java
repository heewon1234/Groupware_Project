package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.services.ChatRoomService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/chats/")
public class ChatController {
	@Autowired
	private HttpSession hsession;
	@Autowired
	private ChatRoomService service;
	
	@RequestMapping("/chatting")
	//@RequestParam("position") String position, Model model
	public String chatting(HttpServletRequest request) {
		String id = (String)hsession.getAttribute("loginId");
		hsession.setAttribute("id", id);
		//model.addAttribute("position", position);
		return "chat/chatting";
	}
	@RequestMapping("/inputText")
	//,@RequestParam("oneSeq") int oneSeq
    public String inputText(@RequestParam("friendName") String friendName,@RequestParam("organization") String organization, Model model) {
        System.out.println(friendName);
        model.addAttribute("friendName", friendName);
        model.addAttribute("organization", organization);
        //model.addAttribute("oneSeq", oneSeq);
        //System.out.println(oneSeq);
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
	
	@RequestMapping("oneAllRecordCount")
    @ResponseBody
    public int getNewRecordCount() {
        int newRecordCount = service.oneCountAll();
        return newRecordCount;
    }
}
