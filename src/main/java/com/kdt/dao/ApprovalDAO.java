package com.kdt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.ApprovalDTO;

@Repository
public class ApprovalDAO {
	@Autowired
	private SqlSession db;
	
	public List<ApprovalDTO> selectbyId(String id) {
		return db.selectList("Approval.selectById", id);
	}
	
	public List<ApprovalDTO> selectWaitbyId(String id) {
		return db.selectList("Approval.selectWaitById", id);
	}
	
	public int insert(ApprovalDTO dto) {
		db.insert("Approval.insert", dto);
		
		return dto.getSeq();
	}
	
	public ApprovalDTO selectByDocId(int docId) {
		return db.selectOne("Approval.selectByDocId", docId);
	}
}
