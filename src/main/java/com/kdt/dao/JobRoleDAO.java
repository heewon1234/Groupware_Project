package com.kdt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.JobRoleDTO;

@Repository
public class JobRoleDAO {
	@Autowired
	private SqlSession db;
	
	public List<JobRoleDTO> selectAll() {
		
		return db.selectList("JobRole.selectAll");
	}
	
	public int insert(JobRoleDTO dto) {
		return db.insert("JobRole.insert", dto);
	}
	
	public int delete(JobRoleDTO dto) {
		return db.delete("JobRole.delete", dto);
	}
}
