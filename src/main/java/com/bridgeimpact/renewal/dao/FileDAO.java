package com.bridgeimpact.renewal.dao;

import com.bridgeimpact.renewal.dto.FileVO;

public interface FileDAO {

	public void insertFile(FileVO file) throws Exception;

	public void editFile(FileVO file) throws Exception;

	public void deleteFile(FileVO file) throws Exception;
	
}
