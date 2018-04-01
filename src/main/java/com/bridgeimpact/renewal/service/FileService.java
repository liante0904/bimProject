package com.bridgeimpact.renewal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bridgeimpact.renewal.dto.ArticleVO;
import com.bridgeimpact.renewal.dto.FileVO;

public interface FileService {
	
	public void insertFile(HttpServletRequest request, ArticleVO article) throws Exception;

	public void editFile(FileVO file) throws Exception;

	public void deleteFile(FileVO file) throws Exception;

	public List<FileVO> selectAllFileByIndex(int num) throws Exception;

	public FileVO selectFileByIndex(int num) throws Exception;

}
