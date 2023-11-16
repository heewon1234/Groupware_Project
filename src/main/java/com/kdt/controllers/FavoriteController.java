package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.dto.FavoriteBoardDTO;
import com.kdt.services.FavoriteService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/favorite/")
public class FavoriteController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	FavoriteService fservice;
	
	@ResponseBody
	@RequestMapping("insertFav")
	public int insertFav(FavoriteBoardDTO dto) {
		String id = (String)session.getAttribute("loginId");
		dto.setId(id);
		return fservice.insertFav(dto);
	}
	
	@ResponseBody
	@RequestMapping("delFavContents")
	public int delFavContents(FavoriteBoardDTO dto) {
		String id = (String)session.getAttribute("loginId");
		dto.setId(id);
		System.out.println("board_title:"+dto.getOri_board_title());
		System.out.println("parent_seq:"+dto.getParent_seq());
	
		return fservice.delFavContents(dto);
	}
	
}
