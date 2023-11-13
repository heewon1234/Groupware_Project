package com.kdt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.OrganizationDTO;

@Repository
public class OrganizationDAO {
	@Autowired
	private SqlSession db;

	public List<OrganizationDTO> selectAll() {

		return db.selectList("Organization.selectAll");
	}
	
	public int insert(OrganizationDTO dto) {
		return db.insert("Organization.insert", dto);
	}
	
	public int delete(String organization) {
		return db.delete("Organization.delete", organization);
	}
	
	public String getManagerOrg(String org) {
		return db.selectOne("Organization.getManagerOrg", org);
	}
}
