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
	public List<ApprovalResponsiblesDTO> getManagerRBList(int docId) {
		return db.selectList("ApprovalResponsibles.getManagerRBList", docId);
	}
	public int updateStatus(ApprovalResponsiblesDTO dto) {
		return db.update("ApprovalResponsibles.updateStatus", dto);
	}
	public int getEveryCount(String id) {
		return db.selectOne("ApprovalResponsibles.getEveryCount", id);
	}
	public int getPendingCount(String id) {
		return db.selectOne("ApprovalResponsibles.getPendingCount", id);
	}
	public int getApproveCount(String id) {
		return db.selectOne("ApprovalResponsibles.getApproveCount", id);
	}
	public int getReturnCount(String id) {
		return db.selectOne("ApprovalResponsibles.getReturnCount", id);
	}
}
