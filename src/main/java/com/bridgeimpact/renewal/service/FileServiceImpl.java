package com.bridgeimpact.renewal.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

//import com.bridgeimpact.renewal.common.FileioUtil;
import com.bridgeimpact.renewal.dao.FileDAO;
import com.bridgeimpact.renewal.dto.ArticleVO;
import com.bridgeimpact.renewal.dto.FileVO;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Autowired
	private FileDAO fileDAO;
	
	@Override
	public void insertFile(HttpServletRequest request, ArticleVO article) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		java.util.Iterator<String> fileNames = multipartRequest.getFileNames();
		FileVO fileVO = new FileVO();
		while(fileNames.hasNext())
		{
			// fileVO 세팅
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			if (!mFile.isEmpty()) {
			String newFileName = "";
			String path = request.getSession().getServletContext().getRealPath("/");
			newFileName = System.currentTimeMillis()+"."
					+mFile.getOriginalFilename().substring(mFile.getOriginalFilename().lastIndexOf(".")+1);
			logger.info(fileName);
			logger.info(path + mFile.getOriginalFilename());
			logger.info(newFileName);
			fileVO.setOriginalFileName(mFile.getOriginalFilename());
			fileVO.setStoredFileName(newFileName);
			fileVO.setFileSize((int)mFile.getSize());
			fileVO.setArticleIdx(article.getIdx());
			fileVO.setCreaId(article.getWriteId());


			File file = new File(path + mFile.getOriginalFilename());
			
			if (mFile.getSize() != 0) // File Null Check
			{
				if (!file.exists()) // 경로상에 파일이 존재하지 않을 경우
				{
					if (file.getParentFile().mkdirs()) // 경로에 해당하는 디렉토리들을 생성
					{
						try {
							file.createNewFile(); // 이후 파일 생성
							logger.info(file.getAbsolutePath() + file.getName());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				try {
					mFile.transferTo(file); //임시로 저장된 multipartFile을 실제 파일로 전송
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fileDAO.insertFile(fileVO);
			}
			}
		}
			
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

	@Override
	public List<FileVO> selectAllFileByIndex(int num) throws Exception {
		// TODO Auto-generated method stub
		return fileDAO.selectAllFileByIndex(num);
	}

	@Override
	public FileVO selectFileByStoredName(String storedNm) throws Exception {
		// TODO Auto-generated method stub
		return fileDAO.selectFileByStoredName(storedNm);
	}

}
