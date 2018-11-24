package com.bridgeimpact.renewal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bridgeimpact.renewal.dto.ArticleVO;
import com.bridgeimpact.renewal.dto.FileVO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface FileService {
	
	public void insertFile(HttpServletRequest request, ArticleVO article) throws Exception;

	public void editFile(FileVO file) throws Exception;

	public boolean deleteFile(HttpServletRequest request, FileVO file) throws Exception;

	public List<FileVO> selectAllFileByIndex(int num) throws Exception;

	public FileVO selectFileByStoredName(String storedNm) throws Exception;

}
