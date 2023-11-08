package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.JobRoleDAO;
import com.kdt.dto.JobRoleDTO;

@Service
public class JobRoleService {
	@Autowired
	private JobRoleDAO dao;

	public List<JobRoleDTO> selectAll() {
		return dao.selectAll();
	}
	
	public int insert(JobRoleDTO dto) {
		return dao.insert(dto);
	}
	
	public int delete(JobRoleDTO dto) {
		return dao.delete(dto);
	}

}
