package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.ApprovalFilesDAO;
import com.kdt.dto.ApprovalFilesDTO;

@Service
public class ApprovalFilesService {
	@Autowired
	private ApprovalFilesDAO dao;
	
	public List<ApprovalFilesDTO> selectByParentSeq(int parent_seq) {
		return dao.selectByParentSeq(parent_seq);
	}
}
