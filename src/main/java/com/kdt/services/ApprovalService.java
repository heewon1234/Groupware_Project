package com.kdt.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.dao.ApprovalDAO;
import com.kdt.dao.ApprovalFilesDAO;
import com.kdt.dao.ApprovalResponsiblesDAO;
import com.kdt.dao.WorkPlanDAO;
import com.kdt.dto.ApplyLeaveDTO;
import com.kdt.dto.ApprovalDTO;
import com.kdt.dto.ApprovalFilesDTO;
import com.kdt.dto.ApprovalResponsiblesDTO;
import com.kdt.dto.WorkPlanDTO;

import jakarta.transaction.Transactional;

@Service
public class ApprovalService {
	@Autowired
	private ApprovalDAO adao;
	@Autowired
	private ApprovalFilesDAO afdao;
	@Autowired
	private ApprovalResponsiblesDAO ardao;
	@Autowired
	private WorkPlanDAO wpdao;
	
	public List<ApprovalDTO> selectById(String id) {
		return adao.selectbyId(id);
	}
	
	public List<ApprovalDTO> selectWaitById(String id) {
		return adao.selectWaitbyId(id);
	}
	
	public List<ApprovalDTO> selectCompleteById(String id) {
		return adao.selectCompleteById(id);
	}
	
	public List<ApprovalDTO> selectProgressById(String id) {
		return adao.selectProgressById(id);
	}
	
	public int delete(int doc_id) {
		return adao.delete(doc_id);
	}
	
	@Transactional
	public void insert(ApprovalDTO dto, MultipartFile[] files, String path, String[] managerID) throws Exception {
		int parent_seq = adao.insert(dto);

		File uploadPath = new File(path);

		if(!uploadPath.exists()) {
			uploadPath.mkdir();
		}

		// 클라이언트에서 submit한 데이터에 files가 존재할 경우
		if(files != null) {
			for(MultipartFile file : files) {
				String oriName = file.getOriginalFilename();
				String sysName = UUID.randomUUID()+"_"+oriName;

				file.transferTo(new File(uploadPath+"/"+sysName));

				afdao.insert(new ApprovalFilesDTO(0,parent_seq, oriName, sysName));
			}
		}
		
		for(String id : managerID) {
			ardao.insert(new ApprovalResponsiblesDTO(0, parent_seq, id, "미결"));
		}
	}
	
	public List<ApprovalDTO> selectListByDocId(List<Integer> docIdList) {
		List<ApprovalDTO> appList = new ArrayList<>();
		
		for(Integer docId : docIdList) {
			appList.add(adao.selectByDocId(docId));
		}
		
		return appList;
	}
	
	public ApprovalDTO selectByDocId(int docId) {
		return adao.selectByDocId(docId);
	}
	
	public int getAllCount(String id) {
		return adao.getAllCount(id);
	}
	
	public int getWaitCount(String id) {
		return adao.getWaitCount(id);
	}
	
	public int getCompleteCount(String id) {
		return adao.getCompleteCount(id);
	}
	
	public int getProcessCount(String id) {
		return adao.getProcessCount(id);
	}
	@Transactional
	public void insertWorkPlan(ApprovalDTO appdto, List<WorkPlanDTO> workPlanDTOList, String[] managerID) {
		int parent_seq = adao.insert(appdto);
		for (WorkPlanDTO wpdto : workPlanDTOList) {
	        wpdto.setDoc_id(parent_seq);
	        wpdao.insert(wpdto);
	    }
		
		for(String id : managerID) {
			ardao.insert(new ApprovalResponsiblesDTO(0, parent_seq, id, "미결"));
		}
	}
	public int leaveinsert(ApprovalDTO adto)throws Exception{
		return adao.insert(adto);
	}
	public void aleaveinsert(ApplyLeaveDTO aldto)throws Exception{
		String list[] = aldto.getWork_days().split(",");

		for(String work_day : list) {
			aldto.setWork_days(work_day);
			adao.aleaveinsert(aldto);
		}

		return;
	}
	public String work_type(int doc_id)throws Exception{
		return adao.work_type(doc_id);
	}
	public int count(int doc_id)throws Exception{
		return adao.count(doc_id);
	}
	public String getID(int doc_id)throws Exception{
		return adao.getID(doc_id);
	}
	
}
