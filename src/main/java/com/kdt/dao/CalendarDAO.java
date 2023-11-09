package com.kdt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.Personal_CalendarDTO;

@Repository
public class CalendarDAO {
	@Autowired
	private SqlSession db;
	
	public List<Personal_CalendarDTO> selectAll(String id) {
		return db.selectList("Calendar.selectAll",id);
	}
	
	public void insert(Personal_CalendarDTO dto){ 
		db.insert("Calendar.insert", dto); 
	}
	
	public int delete(int seq) {
		return db.delete("Calendar.delete",seq);
	}
	
	public int update(Personal_CalendarDTO dto) {
		return db.update("Calendar.update", dto);
	}
}
