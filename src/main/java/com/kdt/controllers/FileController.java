package com.kdt.controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.services.FileService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/file/")
public class FileController {
	
	@Autowired
	FileService fservice;
	
	@ResponseBody
	@RequestMapping("upload")
	public List<String> upload(MultipartFile[] fileList) throws Exception{
		
		List<String> list = new ArrayList<>();
		String realPath = "C:/uploads";
		File uploadPath = new File(realPath); 
		if(!uploadPath.exists()) { 
			uploadPath.mkdir();
		}

		if(fileList != null) { 
			for(MultipartFile file : fileList) {
				String ori_name = file.getOriginalFilename();
				String sys_name = UUID.randomUUID() + "_" + ori_name;
				file.transferTo(new File(uploadPath+"/"+sys_name));
				list.add("/uploads/"+sys_name);
			}
		}
		return list;

	}
	
	@RequestMapping("download")
	public void download(String oriName, String sysName, HttpServletResponse response) throws Exception{
		String realPath =  "C:/uploads";
		File targetFile = new File(realPath + "/" + sysName);

		oriName = new String(oriName.getBytes("utf8"),"ISO-8859-1");
		response.setHeader("content-disposition", "attachment;filename="+oriName);

		try(DataInputStream dis = new DataInputStream(new FileInputStream(targetFile));
			DataOutputStream dos = new DataOutputStream(response.getOutputStream());){
			byte[] fileContents = dis.readAllBytes();
			dos.write(fileContents);
			dos.flush();
		}	
	}
	
	@ResponseBody
	@RequestMapping(value="/imageUpload",method=RequestMethod.POST)
	public List<String> imageUpload(MultipartFile[] files) throws Exception {
		// 이미지를 저장할 디렉토리 경로 설정
		String upload = "C:/uploads";

		List<String> fileUrls = fservice.imageUpload(files, upload);

		return fileUrls;
	}
	
	@ResponseBody
	@RequestMapping("deleteServerFile")
	public void deleteServerFile(String sys_name) throws Exception{
		fservice.deleteServerFile(sys_name);
	}
	
}
