package com.kdt.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.FileDAO;
import com.kdt.dto.FileDTO;

@Service
public class FileService {
	
	@Autowired
	FileDAO fdao;
	
	public void deleteServerFile(String sys_name) throws Exception{
		String realPath = "E:/uploads";
		File uploadPath = new File(realPath);
		Path path = Paths.get(uploadPath + "/" + sys_name);
		Files.deleteIfExists(path);
	}
	
	public List<FileDTO> selectFileList(FileDTO dto){
		return fdao.selectFileList(dto);
	}
	
}
