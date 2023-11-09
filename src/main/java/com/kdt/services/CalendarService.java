package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.CalendarDAO;
import com.kdt.dto.Official_CalendarDTO;
import com.kdt.dto.Personal_CalendarDTO;

@Service
public class CalendarService {
	@Autowired
	public CalendarDAO cdao;
	
	public List<Personal_CalendarDTO> selectAllP(String id) {
		return this.cdao.selectAllP(id);
	}
	
	public List<Official_CalendarDTO> selectAllO(String org) {
		return this.cdao.selectAllO(org);
	}
	
	public void pinsert(Personal_CalendarDTO dto) {
		this.cdao.pinsert(dto);
	}
	
	public void oinsert(Official_CalendarDTO dto) {
		this.cdao.oinsert(dto);
	}
	
	public int pdelete(int seq) {
		return this.cdao.pdelete(seq);
	}
	
	public int odelete(int seq) {
		return this.cdao.odelete(seq);
	}
	
	public int pupdate(Personal_CalendarDTO dto) {
		return this.cdao.pupdate(dto);
	}
	
	public int oupdate(Official_CalendarDTO dto) {
		return this.cdao.oupdate(dto);
	}
}
