package com.kdt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.Mk_BoardDTO;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSession db;

	public List<Mk_BoardDTO> select_board_type_group(){
		return db.selectList("Board.select_board_type_group");
	}
	
	public List<Mk_BoardDTO> select_board_type_all(){
		return db.selectList("Board.select_board_type_all");
	}
}
