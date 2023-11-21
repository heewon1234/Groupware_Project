package com.kdt.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.dao.FileDAO;
import com.kdt.dao.MembersDAO;
import com.kdt.dto.FileDTO;
import com.kdt.dto.MembersDTO;

@Service
public class FileService {
	
	@Autowired
	private FileDAO fdao;
	@Autowired
	private MembersDAO mdao;
	
	public void deleteServerFile(String sys_name) throws Exception{
		String realPath = "C:/uploads";
		File uploadPath = new File(realPath);
		Path path = Paths.get(uploadPath + "/" + sys_name);
		Files.deleteIfExists(path);
	}
	
	public List<FileDTO> selectFileList(FileDTO dto){
		return fdao.selectFileList(dto);
	}
	
	public List<String> imageUpload(MultipartFile[] files, String path) throws Exception {
		List<String> fileUrls = new ArrayList<>();

		// 디렉토리가 없을 경우 생성
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		if(files != null) {//클라이언트에서 submit 한 데이터에 files 가 존재할 경우
			for(MultipartFile file: files) {
				String oriName = file.getOriginalFilename();
				String sysName = UUID.randomUUID()+"_"+oriName;//파일이름이 겹치지 않게 해준다.
				//-----아직 임시 저장소
				file.transferTo(new File(dir+"/"+sysName));
				fileUrls.add("/uploads/profile_image/" + sysName);
			}
		}
		
		return fileUrls;

	}
	
}
