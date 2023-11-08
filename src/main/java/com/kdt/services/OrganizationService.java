package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.OrganizationDAO;
import com.kdt.dto.OrganizationDTO;

@Service
public class OrganizationService {
	@Autowired
	private OrganizationDAO dao;
	
	public List<OrganizationDTO> selectAll() {
		return dao.selectAll();
	}
	
	public int insert(OrganizationDTO dto) {
		return dao.insert(dto);
	}
	
	public int delete(String organization) {
		return dao.delete(organization);
	}
}