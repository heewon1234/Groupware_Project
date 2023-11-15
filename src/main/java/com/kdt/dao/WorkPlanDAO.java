package com.kdt.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.WorkPlanDTO;

@Repository
public class WorkPlanDAO {
	
	@Autowired
	private SqlSession db;
	
	public void insert(WorkPlanDTO dto) {
		System.out.println(dto.getDoc_id());
		db.insert("WorkPlan.insert", dto);
	}

}
