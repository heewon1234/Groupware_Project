package com.kdt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.WorkTimesDTO;
import com.kdt.dto.WorksDTO;
@Repository
public class WorksDAO {

	@Autowired
	private SqlSession db;
	
	public List<WorksDTO> select(String id)throws Exception{
		//String sql = "select * from board2 where seq=? order by seq desc;";
		return db.selectList("Works.select",id);
	}
	public List<WorkTimesDTO> selectby(String id)throws Exception{
		//String sql = "select * from board2 where seq=? order by seq desc;";
		return db.selectList("Worktimes.selectby",id);
	}
	
	public int insert(WorkTimesDTO dto)throws Exception{
		return db.insert("Worktimes.insert", dto);
	}
	
	public List<WorkTimesDTO> work_inout(String id)throws Exception{
		return db.selectList("Worktimes.work_inout",id);
	}
}
