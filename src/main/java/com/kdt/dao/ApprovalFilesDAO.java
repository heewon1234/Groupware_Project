package com.kdt.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.ApprovalFilesDTO;

@Repository
public class ApprovalFilesDAO {
	@Autowired
	private SqlSession db;
	
	public int insert(ApprovalFilesDTO dto) {
		return db.insert("ApprovalFiles.insert", dto);
	}
}
