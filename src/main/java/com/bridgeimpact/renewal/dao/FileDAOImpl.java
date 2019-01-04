package com.bridgeimpact.renewal.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bridgeimpact.renewal.dto.FileVO;

@Repository
public class FileDAOImpl implements FileDAO{

    @Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.bridgeimpact.renewal.fileSQL";
    
	
	@Override
	public void insertFile(FileVO file) {
		// TODO Auto-generated method stub
		sqlSession.insert(Namespace+".insertFile", file);
	}

	@Override
	public void editFile(FileVO file) {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace+".editFile", file);
	}

	@Override
	public boolean deleteFile(FileVO file) {
		boolean result = false;
		int resultCnt = sqlSession.delete(Namespace+".deleteFile", file);
		if (resultCnt == 1)
			result = true;
		return result;
	}

	@Override
	public List<FileVO> selectAllFileByIndex(int num) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+".selectAllFileByIndex", num);
	}

	@Override
	public FileVO selectFileByStoredName(String storedNm) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace+".selectFileByStoredName", storedNm);
	}

}
