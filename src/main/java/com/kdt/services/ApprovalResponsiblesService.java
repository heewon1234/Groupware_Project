package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.ApprovalResponsiblesDAO;
import com.kdt.dto.ApprovalResponsiblesDTO;

@Service
public class ApprovalResponsiblesService {
	@Autowired
	private ApprovalResponsiblesDAO dao;
	
	public List<Integer> getDocIdByUserId(String id) {
		return dao.getDocIdByUserId(id);
	}
	public List<Integer> getPendingDocIdByUserId(String id) {
		return dao.getPendingDocIdByUserId(id);
	}
	public List<Integer> getApprovalDocIdByUserId(String id) {
		return dao.getApprovalDocIdByUserId(id);
	}
	public List<Integer> getReturnDocIdByUserId(String id) {
		return dao.getReturnDocIdByUserId(id);
	}
	
	public List<ApprovalResponsiblesDTO> getManagerRBList(int docId) {
		return dao.getManagerRBList(docId);
	}
	public int updateStatus(ApprovalResponsiblesDTO dto) {
		return dao.updateStatus(dto);
	}
	
	public int getEveryCount(String id) {
		return dao.getEveryCount(id);
	}
	
	public int getPendingCount(String id) {
		return dao.getPendingCount(id);
	}
	
	public int getApprovalCount(String id) {
		return dao.getApprovalCount(id);
	}
	
	public int getReturnCount(String id) {
		return dao.getReturnCount(id);
	}
}
