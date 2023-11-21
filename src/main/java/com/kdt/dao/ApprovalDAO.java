package com.kdt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.ApplyLeaveDTO;
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
	
	public List<ApprovalDTO> selectCompleteById(String id) {
		return db.selectList("Approval.selectCompleteById", id);
	}
	
	public List<ApprovalDTO> selectProgressById(String id) {
		return db.selectList("Approval.selectProgressById", id);
	}
	
	public int insert(ApprovalDTO dto) {
		db.insert("Approval.insert", dto);
		
		return dto.getSeq();
	}
	
	public int delete(int doc_id) {
		return db.delete("Approval.delete", doc_id);
	}
	
	public ApprovalDTO selectByDocId(int docId) {
		return db.selectOne("Approval.selectByDocId", docId);
	}
	
	public int getAllCount(String id) {
		return db.selectOne("Approval.getAllCount", id);
	}
	
	public int getWaitCount(String id) {
		return db.selectOne("Approval.getWaitCount", id);
	}
	
	public int getCompleteCount(String id) {
		return db.selectOne("Approval.getCompleteCount", id);
	}
	
	public int getProcessCount(String id) {
		return db.selectOne("Approval.getProcessCount", id);
	}
	public int leaveinsert(ApprovalDTO adto)throws Exception{
		return db.insert("Approval.leaveinsert",adto);
	}
	public int aleaveinsert(ApplyLeaveDTO aldto)throws Exception{
		return db.insert("Approval.aleaveinsert",aldto);
	}
}
