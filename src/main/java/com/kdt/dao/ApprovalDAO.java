package com.kdt.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.ApprovalDTO;

@Repository
public class ApprovalDAO {
	@Autowired
	private SqlSession db;
	
	public int insert(ApprovalDTO dto) {
		db.insert("Approval.insert", dto);
		
		return dto.getSeq();
	}
}
