package com.bridgeimpact.renewal.dao;

import java.util.List;

import com.bridgeimpact.renewal.dto.FileVO;

public interface FileDAO {

	void insertFile(FileVO file) throws Exception;

	void editFile(FileVO file) throws Exception;

	boolean deleteFile(FileVO file) throws Exception;

	List<FileVO> selectAllFileByIndex(int num) throws Exception;

	FileVO selectFileByStoredName(String storedNm) throws Exception;
	
}
