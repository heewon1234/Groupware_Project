package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.MembersDAO1;
import com.kdt.dto.MembersDTO1;

@Service
public class MembersService1 {
	@Autowired//자동으로 연결됨
	private MembersDAO1 dao;
	
	public List<MembersDTO1> selectAll() {
		return dao.selectAll();
	}
	public List<MembersDTO1> getMembersByOrganization(String organization) {
		return dao.getMembersByOrganization(organization);
	}
}
