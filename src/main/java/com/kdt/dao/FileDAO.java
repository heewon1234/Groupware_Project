package com.kdt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.FileDTO;

@Repository
public class FileDAO {

	@Autowired
	private SqlSession db;
	
	public int insertFile(FileDTO dto) {
		return db.insert("File.insertFile",dto);
	}
	
	public List<FileDTO> selectFileList(FileDTO dto){
		return db.selectList("File.selectFileList",dto);
	}
	public List<String> selectFileByBoardTitle(String board_title){
		return db.selectList("File.selectFileByBoardTitle",board_title);
	}
	
	
	public int delFileByParensSeq(FileDTO dto) {
		return db.delete("File.delFileByParensSeq",dto);
	}
	public int delFileBySysName(String sys_name) {
		return db.delete("File.delFileBySysName",sys_name);
	}
	public int delFileTag(FileDTO dto) {
		return db.delete("File.delFileTag",dto);
	}
}
