package com.kdt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.FavoriteBoardDAO;
import com.kdt.dao.Mk_BoardDAO;
import com.kdt.dto.FavoriteBoardDTO;

@Service
public class FavoriteService {
	
	@Autowired
	FavoriteBoardDAO fdao;
	
	@Autowired
	Mk_BoardDAO mdao;
	
	public int insertFav(FavoriteBoardDTO dto) {
		int seq = mdao.selectBoardSeq(dto.getBoard_title());
		dto.setBoard_title("Board_"+seq);
		return fdao.insertFav(dto);
	}
	
	public int delFavContents(FavoriteBoardDTO dto) {
		return fdao.delFavContents(dto);
	}
	
}
