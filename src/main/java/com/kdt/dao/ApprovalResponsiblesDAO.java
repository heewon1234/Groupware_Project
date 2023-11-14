package com.kdt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.ApprovalResponsiblesDTO;

@Repository
public class ApprovalResponsiblesDAO {
	@Autowired
	private SqlSession db;
	
	public int insert(ApprovalResponsiblesDTO dto) {
		return db.insert("ApprovalResponsibles.insert", dto);
	}
	public List<Integer> getDocIdByUserId(String id) {
		return db.selectList("ApprovalResponsibles.getDocIdByUserId", id);
	}
	public List<Integer> getPendingDocIdByUserId(String id) {
		return db.selectList("ApprovalResponsibles.getPendingDocIdByUserId", id);
	}
	public List<Integer> getApproveDocIdByUserId(String id) {
		return db.selectList("ApprovalResponsibles.getApproveDocIdByUserId", id);
	}
	public List<Integer> getReturnDocIdByUserId(String id) {
		return db.selectList("ApprovalResponsibles.getReturnDocIdByUserId", id);
	}
	public List<String> getManagerIdList(int docId) {
		return db.selectList("ApprovalResponsibles.getManagerIdList", docId);
	}
	public int updateStatus(ApprovalResponsiblesDTO dto) {
		return db.update("ApprovalResponsibles.updateStatus", dto);
	}
}
