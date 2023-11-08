package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.CalendarDAO;
import com.kdt.dto.Personal_CalendarDTO;

@Service
public class CalendarService {
	@Autowired
	public CalendarDAO cdao;
	
	public List<Personal_CalendarDTO> selectAll(String id) {
		return this.cdao.selectAll(id);
	}
	
	public void insert(Personal_CalendarDTO dto) {
		this.cdao.insert(dto);
	}
	
	public int delete(int seq) {
		return this.cdao.delete(seq);
	}
	
	public int update(Personal_CalendarDTO dto) {
		return this.cdao.update(dto);
	}
}
