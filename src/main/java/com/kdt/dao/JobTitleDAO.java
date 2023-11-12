package com.kdt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.JobTitleDTO;

@Repository
public class JobTitleDAO {
	@Autowired
	private SqlSession db;
	
	public List<JobTitleDTO> selectAll() {
		
		return db.selectList("JobTitle.selectAll");
	}
	
	public int insert(JobTitleDTO dto) {
		return db.insert("JobTitle.insert", dto);
	}
	
	public int delete(String position) {
		return db.delete("JobTitle.delete", position);
	}
	
	public List<String> getManagerPosition(String position) {
		return db.selectList("JobTitle.getManagerPosition", position);
	}
}
