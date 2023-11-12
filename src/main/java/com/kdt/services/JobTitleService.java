package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.JobTitleDAO;
import com.kdt.dto.JobTitleDTO;

@Service
public class JobTitleService {
	@Autowired
	private JobTitleDAO dao;

	public List<JobTitleDTO> selectAll() {
		return dao.selectAll();
	}
	
	public int insert(JobTitleDTO dto) {
		return dao.insert(dto);
	}
	
	public int delete(String position) {
		return dao.delete(position);
	}
	
	public List<String> getManagerPosition(String position) {
		return dao.getManagerPosition(position);
	}
}
