package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.ApprovalResponsiblesDAO;

@Service
public class ApprovalResponsiblesService {
	@Autowired
	private ApprovalResponsiblesDAO dao;
	
	public List<Integer> getDocIdByUserId(String id) {
		return dao.getDocIdByUserId(id);
	}
	
	public List<String> getManagerIdList(int docId) {
		return dao.getManagerIdList(docId);
	}
}
