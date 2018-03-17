package com.bridgeimpact.renewal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeimpact.renewal.dao.FileDAO;
import com.bridgeimpact.renewal.dto.FileVO;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileDAO fileDAO;
	
	@Override
	public void insertFile(FileVO file) throws Exception {
		// TODO Auto-generated method stub
		fileDAO.insertFile(file);
	}

	@Override
	public void editFile(FileVO file) throws Exception {
		// TODO Auto-generated method stub
		fileDAO.editFile(file);
	}

	@Override
	public void deleteFile(FileVO file) throws Exception {
		// TODO Auto-generated method stub
		fileDAO.deleteFile(file);
	}

}
