package com.bridgeimpact.renewal.service;

import com.bridgeimpact.renewal.dto.FileVO;

public interface FileService {
	
	public void insertFile(FileVO file) throws Exception;

	public void editFile(FileVO file) throws Exception;

	public void deleteFile(FileVO file) throws Exception;

}
