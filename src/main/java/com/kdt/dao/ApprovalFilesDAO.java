package com.kdt.dao;

import java.util.List;

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
	public List<ApprovalFilesDTO> selectByParentSeq(int parent_seq) {
		return db.selectList("ApprovalFiles.selectByParentSeq", parent_seq);
	}
}
