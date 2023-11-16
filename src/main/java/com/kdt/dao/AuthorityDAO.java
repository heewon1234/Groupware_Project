package com.kdt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.AuthorityDTO;

@Repository
public class AuthorityDAO {
	
	@Autowired
	private SqlSession db;
	
	public boolean isExistAuth(Map<String,String> map) {
		return db.selectOne("Authority.isExistAuth",map);
	}
	
	public int authorityInsert(AuthorityDTO dto) {
		return db.insert("Authority.authorityInsert",dto);
	}
	
	public List<String> selectAuthBoard(String id){
		return db.selectList("Authority.selectAuthBoard",id);
	}
	
	public List<Map<String,String>> selectAuthMember(String board_title){
		return db.selectList("Authority.selectAuthMember",board_title);
	}
	
	public int deleteAuthority(String preBoardTitle) {
		return db.delete("Authority.deleteAuthority",preBoardTitle);
	}
	
}
