package com.kdt.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.dto.MembersDTO;
import com.kdt.dto.OneToOneChatDTO;
import com.kdt.services.ChatRoomService;
import com.kdt.services.MembersService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/chats/")
public class ChatController {
	@Autowired
	private HttpSession hsession;
	@Autowired
	private ChatRoomService service;
	@Autowired
	private MembersService mservice;
	
	@RequestMapping("/chatting")
	public String chatting(HttpSession session,HttpServletRequest request,Model model) {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		userDTO = mservice.loginUser(userDTO.getId());
		model.addAttribute("userDTO", userDTO);
		return "chat/chatting";
	}
	@RequestMapping("/groupInputText")
	public String inputText(@RequestParam("groupName") String groupName,@RequestParam("groupSeq") int groupSeq, Model model) {
		model.addAttribute("groupName", groupName);
		model.addAttribute("groupSeq", groupSeq);
		return "chat/groupInputText";
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
	//유저 검색
	@RequestMapping("searchUser")
	@ResponseBody
	public Map<String, Object> searchUser(@RequestParam String searchValue) throws Exception{
		String id = (String)hsession.getAttribute("loginID");
		List<MembersDTO> searchList = service.searchUser(searchValue);
		List<OneToOneChatDTO> OneToOneChatDTOList = service.selectAll(id);
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("searchList", searchList);
		responseData.put("OneToOneChatDTOList", OneToOneChatDTOList);
		return responseData;
	}
	
	@RequestMapping("oneAllRecordCount")
    @ResponseBody
    public int getNewRecordCount() {
		String id = (String)hsession.getAttribute("loginId");
        int newRecordCount = service.oneCountAll(id);
        return newRecordCount;
    }
	
}
