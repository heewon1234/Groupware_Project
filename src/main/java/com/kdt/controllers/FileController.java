package com.kdt.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file/")
public class FileController {
	
	@ResponseBody
	@RequestMapping("upload")
	public List<String> upload(MultipartFile[] fileList) throws Exception{
		
		List<String> list = new ArrayList<>();
		String realPath = "E:/uploads";
		File uploadPath = new File(realPath); 
		if(!uploadPath.exists()) { 
			uploadPath.mkdir();
		}

		if(fileList != null) { 
			for(MultipartFile file : fileList) {
				String ori_name = file.getOriginalFilename();
				String sys_name = UUID.randomUUID() + "_" + ori_name;
				System.out.println(sys_name);
				file.transferTo(new File(uploadPath+"/"+sys_name));
				list.add("/uploads/"+sys_name);
			}
		}
		return list;

	}
	
}
