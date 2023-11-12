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

import com.kdt.dto.GroupChatDTO;
import com.kdt.dto.MembersDTO;
import com.kdt.dto.OneToOneChatDTO;
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
		//String id = (String)hsession.getAttribute("loginId");
		//String name = (String)hsession.getAttribute("name");
		//String position = (String)hsession.getAttribute("position");
		return "chat/chatting";
	}
//	@RequestMapping("/chatting")
//	//@RequestParam("position") String position, Model model
//	public String chatting(HttpServletRequest request,@RequestParam("search_input") String searchInput) {
//		System.out.println(searchInput);
//		//String id = (String)hsession.getAttribute("loginId");
//		//String name = (String)hsession.getAttribute("name");
//		//String position = (String)hsession.getAttribute("position");
//		return "chat/chatting";
//	}
	/*@RequestMapping("/inputText") 이 코드 안씀
    public String inputText(@RequestParam("friendName") String friendName,@RequestParam("organization") String organization,@RequestParam("oneSeq") int oneSeq, Model model) {
        System.out.println(friendName);
        model.addAttribute("friendName", friendName);
        model.addAttribute("organization", organization);
        model.addAttribute("oneSeq", oneSeq);
        System.out.println(oneSeq);
        return "chat/inputText";
    }*/
	@RequestMapping("/groupInputText")
	public String inputText(@RequestParam("groupName") String groupName,@RequestParam("groupSeq") int groupSeq, Model model) {
		model.addAttribute("groupName", groupName);
		System.out.println(groupName);
		model.addAttribute("groupSeq", groupSeq);
		System.out.println(groupSeq);
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
		System.out.println(OneToOneChatDTOList);
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
