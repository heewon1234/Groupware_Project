package com.kdt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.LeavesDTO;
import com.kdt.dto.MembersDTO;
import com.kdt.dto.WorkPlanDTO;
import com.kdt.dto.WorkTimesDTO;
import com.kdt.dto.WorksDTO;
import com.kdt.dto.WorkstatisticsDTO;
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
	
	public int leave_insert(LeavesDTO dto)throws Exception{
		return db.insert("Leaves.insert",dto);
	}
	public int work_insert(WorksDTO dto)throws Exception{
		return db.insert("Works.insert",dto);
	}
	public int leave_update(LeavesDTO dto) {
		return db.update("Leaves.update", dto);
	}
	public int work_update(WorksDTO dto) {
		return db.update("Works.update", dto);
	}
	public int leave_delete(String idList)throws Exception{
		return db.delete("Leaves.delete",idList);
	}
	public int work_delete(String idList)throws Exception{
		return db.delete("Works.delete",idList);
	}
	public List<WorkTimesDTO> work_inout(String id)throws Exception{
		return db.selectList("Worktimes.work_inout",id);
	}
	public List<WorkstatisticsDTO> statisticslist()throws Exception{
		return db.selectList("Works.selectList");
	}
	public List<LeavesDTO> leaveslist()throws Exception{
		return db.selectList("Leaves.list");
	}
	public List<LeavesDTO> leavenull()throws Exception{
		return db.selectList("Leaves.leavenull");
	}
	public int leave_joindayupdate(LeavesDTO dto)throws Exception{
		return db.update("Leaves.joindayupdate",dto);
	}
	public int updateLeaveRemainder(LeavesDTO dto)throws Exception{
		return db.update("Leaves.updateLeaveRemainder",dto);
	}
	public int existstoday(String ID)throws Exception{
		return db.selectOne("Works.existstoday",ID);
	}
	public int addworklate(String ID)throws Exception{
		return db.update("Works.addworklate",ID);
	}
	public int addworkearly(String ID)throws Exception{
		return db.update("Works.addworkearly",ID);
	}
	public int addworkday(String ID)throws Exception{
		return db.update("Works.addworkday",ID);
	}
	
	public int addworkminutetime(String ID)throws Exception{
		return db.update("Works.addworkminutetime",ID);
	}
	public int addworknotcheck(String ID)throws Exception{
		return db.update("Works.addworknotcheck",ID);
	}
	public List<WorkPlanDTO> work_current_selectByName(String name)throws Exception{
		return db.selectList("Works.work_current_selectByName",name);
	}
	
}
