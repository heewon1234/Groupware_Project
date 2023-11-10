package com.kdt.services;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.dao.ApprovalDAO;
import com.kdt.dao.ApprovalFilesDAO;
import com.kdt.dto.ApprovalDTO;
import com.kdt.dto.ApprovalFilesDTO;

import jakarta.transaction.Transactional;

@Service
public class ApprovalService {
	@Autowired
	private ApprovalDAO adao;
	@Autowired
	private ApprovalFilesDAO afdao;
	
	@Transactional
	public void insert(ApprovalDTO dto, MultipartFile[] files, String path) throws Exception {
		int parent_seq = adao.insert(dto);

		File uploadPath = new File(path);

		if(!uploadPath.exists()) {
			uploadPath.mkdir();
		}
		
		System.out.println(parent_seq);

		// 클라이언트에서 submit한 데이터에 files가 존재할 경우
		if(files != null) {
			for(MultipartFile file : files) {
				String oriName = file.getOriginalFilename();
				String sysName = UUID.randomUUID()+"_"+oriName;

				file.transferTo(new File(uploadPath+"/"+sysName));

				afdao.insert(new ApprovalFilesDTO(0,parent_seq, oriName, sysName));
			}
		}
	}
}
