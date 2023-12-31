package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.WorksDAO;
import com.kdt.dto.LeavesDTO;
import com.kdt.dto.WorkPlanDTO;
import com.kdt.dto.WorkTimesDTO;
import com.kdt.dto.WorksDTO;
import com.kdt.dto.WorkstatisticsDTO;

@Service
public class WorksService {

	@Autowired
	private WorksDAO wdao;

	public List<WorksDTO> select(String id)throws Exception{
		return wdao.select(id);
	}
	public List<WorkTimesDTO> selectby(String id)throws Exception{
		return wdao.selectby(id);
	}
	public int insert(WorkTimesDTO dto)throws Exception{
		return wdao.insert(dto);
	}
	public int leave_insert(LeavesDTO dto)throws Exception{
		return wdao.leave_insert(dto);
	}
	public int work_insert(WorksDTO dto)throws Exception{
		return wdao.work_insert(dto);
	}
	public void leave_delete(String idList)throws Exception {
		String list[] = idList.split(",");

		for(String id : list) {
			wdao.leave_delete(id);
		}
		return;
	}
	public void work_delete(String idList)throws Exception {
		String list[] = idList.split(",");

		for(String id : list) {
			wdao.work_delete(id);
		}
		return;
	}
	public void work_update(String idList, String org) {
		String list[] = idList.split(",");

		for(String id : list) {
			WorksDTO dto = new WorksDTO();
			dto.setId(id);
			dto.setOrganization(org);
			wdao.work_update(dto);
		}

		return;
	}
	public void leave_update(String idList, String org) {
		String list[] = idList.split(",");

		for(String id : list) {
			LeavesDTO dto = new LeavesDTO();
			dto.setId(id);
			dto.setOrganization(org);
			wdao.leave_update(dto);
		}

		return;
	}

	public List<WorkTimesDTO> work_inout(String id)throws Exception{
		return wdao.work_inout(id);
	}
	public List<WorkstatisticsDTO> statisticslist()throws Exception{
		return wdao.statisticslist();
	}
	public List<LeavesDTO> leaveslist()throws Exception{
		return wdao.leaveslist();
	}
	public List<LeavesDTO> leavenull()throws Exception{
		return wdao.leavenull();
	}
	public void leave_joindayupdate(String idList, String joindayList) throws Exception {


		String idArray[] = idList.split(",");
		String joindayArray[] = joindayList.split(",");

		for (int i = 0; i < idArray.length; i++) {
			LeavesDTO dto = new LeavesDTO();
			dto.setId(idArray[i]);

			if (i < joindayArray.length && joindayArray[i] != null && !joindayArray[i].isEmpty()) {
				dto.setJoinday(joindayArray[i]);
			} else {
				dto.setJoinday(null);
			}

			wdao.leave_joindayupdate(dto);
		}
		return;
	}
	public void updateLeaveRemainder(String idList, String joindayList) throws Exception {


		String idArray[] = idList.split(",");
		String joindayArray[] = joindayList.split(",");

		for (int i = 0; i < idArray.length; i++) {
			LeavesDTO dto = new LeavesDTO();
			dto.setId(idArray[i]);

			if (i < joindayArray.length && joindayArray[i] != null && !joindayArray[i].isEmpty()) {
				dto.setJoinday(joindayArray[i]);
			} else {
				dto.setJoinday(null);
			}

			wdao.updateLeaveRemainder(dto);
		}
		return;
	}
	public int existstoday(String ID)throws Exception{
		return wdao.existstoday(ID);
	}
	public int addworklate(String ID)throws Exception{
		return wdao.addworklate(ID);
	}
	public int addworkearly(String ID)throws Exception{
		return wdao.addworkearly(ID);
	}
	public List<WorkPlanDTO> work_current_selectByName(String name)throws Exception{
		return wdao.work_current_selectByName(name);
	}

public int addworkday(String ID)throws Exception{
	return wdao.addworkday(ID);
	}

public int addworkminutetime(String ID)throws Exception{
	return wdao.addworkminutetime(ID);
}
public int addworknotcheck(String ID)throws Exception{
	return wdao.addworknotcheck(ID);
}
public String selectbyname(String name)throws Exception{
	return wdao.selectbyname(name);
}
public int updateyleave(WorksDTO dto)throws Exception{
	return wdao.updateyleave(dto);
}
public int updatefleave(WorksDTO dto)throws Exception{
	return wdao.updatefleave(dto);
}
public int updateleave(WorksDTO dto)throws Exception{
	return wdao.updateleave(dto);
}
}
