package com.kdt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.Official_CalendarDTO;
import com.kdt.dto.Personal_CalendarDTO;

@Repository
public class CalendarDAO {
	@Autowired
	private SqlSession db;
	
	public List<Personal_CalendarDTO> selectAllP(String id) {
		return db.selectList("Calendar.selectAllP",id);
	}
	
	public List<Official_CalendarDTO> selectAllO(String org) {
		return db.selectList("Calendar.selectAllO",org);
	}
	
	public void pinsert(Personal_CalendarDTO dto){ 
		db.insert("Calendar.pinsert", dto); 
	}
	
	public void oinsert(Official_CalendarDTO dto){ 
		db.insert("Calendar.oinsert", dto); 
	}
	
	public int pdelete(int seq) {
		return db.delete("Calendar.pdelete",seq);
	}
	
	public int odelete(int seq) {
		return db.delete("Calendar.odelete",seq);
	}
	
	public int pupdate(Personal_CalendarDTO dto) {
		return db.update("Calendar.pupdate", dto);
	}
	
	public int oupdate(Official_CalendarDTO dto) {
		return db.update("Calendar.oupdate", dto);
	}
}
