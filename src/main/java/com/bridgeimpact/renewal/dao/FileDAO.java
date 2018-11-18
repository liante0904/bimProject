package com.bridgeimpact.renewal.dao;

import java.util.List;

import com.bridgeimpact.renewal.dto.FileVO;

public interface FileDAO {

	public void insertFile(FileVO file) throws Exception;

	public void editFile(FileVO file) throws Exception;

	public void deleteFile(FileVO file) throws Exception;

	public List<FileVO> selectAllFileByIndex(int num) throws Exception;

	public FileVO selectFileByStoredName(String storedNm) throws Exception;
	
}
