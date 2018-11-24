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
			if (!mFile.isEmpty())
			{
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

				File file = new File(path + fileVO.getStoredFileName());
			
				if (mFile.getSize() != 0) // File Null Check
				{
					if (!file.exists()) // 경로상에 파일이 존재하지 않을 경우
					{
						System.out.println("최종 저장전 경로 ?? : "+file.getAbsolutePath());
						logger.info("최종 저장전 경로 ?? : "+file.getAbsolutePath());
						if (file.getParentFile().mkdirs()) // 경로에 해당하는 디렉토리들을 생성
						{
							try {
								file.createNewFile(); // 이후 파일 생성
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
	public boolean deleteFile(HttpServletRequest request, FileVO file) throws Exception {
		boolean result = false;
		int getArticleIdx  = file.getArticleIdx();
		String getStoredFileName = file.getStoredFileName();
		String getCreaId = file.getCreaId();
		System.out.println("/* 유효성 체크 */");
		/* 유효성 체크 */
		if ( getArticleIdx == 0 || "".equals(getStoredFileName) || "".equals(getCreaId))
			return result;
		System.out.println("/* 파일 삭제 요청자 아이디와 첨부된 파일의 작성자 아이디 일치여부 확인 */");
		/* 파일 삭제 요청자 아이디와 첨부된 파일의 작성자 아이디 일치여부 확인 */
		result = this.checkFileUploaderByfileVO(file);
		if (!result)
			return result;

		/* file 테이블 삭제 로직*/
		System.out.println("/* file 테이블 삭제 로직*/");
		result = fileDAO.deleteFile(file);

		System.out.println("/* 실제파일 삭제*/");
		/* file 테이블 데이터 삭제 후 실제 저장된 파일 삭제 로직 */
		if (result){
			this.deleteStoredFile(request, file);
		}
		return result;
	}

	private void deleteStoredFile(HttpServletRequest request, FileVO fileVO) {

		String serverPath = request.getSession().getServletContext().getRealPath("/");
		serverPath += fileVO.getStoredFileName();
		logger.info("callDownload : " + serverPath);

		File deleteFile = new File(serverPath);
		logger.info("파일 삭제 명령 전 or 존재 여부 판별 exists()" + deleteFile.exists());
		/* 파일 존재 여부 확인 후 삭제 로직 */
		if (deleteFile.exists()) {
			deleteFile.delete();
			logger.info("파일 삭제 명령 후 존재 여부 확인 ");
			logger.info("파일 삭제 or 존재 여부 판별 exists()" + deleteFile.exists());

		}else {
			new Exception("File can't read(이미 삭제되거나 올바르지 않은 파일 삭제 명령입니다.)");
		}

	}

	private boolean checkFileUploaderByfileVO(FileVO file) {
		String requestId = file.getCreaId();
		FileVO dbFile = new FileVO();
		try {
			dbFile = fileDAO.selectFileByStoredName(file.getStoredFileName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (requestId.equals(dbFile.getCreaId()))
			return true;

		return false;
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
