package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.BoardDAO;
import com.kdt.dto.Mk_BoardDTO;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO bdao;
	
	public List<Mk_BoardDTO> select_board_type_group(){
		return bdao.select_board_type_group();
	}
	
	public List<Mk_BoardDTO> select_board_type_all(){
		return bdao.select_board_type_all();
	}
	
}
