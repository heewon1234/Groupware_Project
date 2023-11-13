package com.kdt.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.FavoriteBoardDTO;

@Repository
public class FavoriteBoardDAO {
	
	@Autowired
	private SqlSession db;
	
	public int insertFav(FavoriteBoardDTO dto) {
		db.insert("Favorite_Board.insertFav",dto);
		return dto.getSeq();
	}
	
	public int delFavContents(FavoriteBoardDTO dto) {
		return db.delete("Favorite_Board.delFavContents",dto);
	}
	
}
