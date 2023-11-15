package com.kdt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.WorkPlanDAO;
import com.kdt.dto.WorkPlanDTO;

@Service
public class WorkPlanService {
	@Autowired
	WorkPlanDAO dao;
	
	public void insert(WorkPlanDTO dto) {
		dao.insert(dto);
	}
}
