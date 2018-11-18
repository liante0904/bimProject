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
	public void insertFile(FileVO file) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(Namespace+".insertFile", file);
	}

	@Override
	public void editFile(FileVO file) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace+".editFile", file);
	}

	@Override
	public void deleteFile(FileVO file) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(Namespace+".deleteFile", file);
	}

	@Override
	public List<FileVO> selectAllFileByIndex(int num) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+".selectAllFileByIndex", num);
	}

	@Override
	public FileVO selectFileByStoredName(String storedNm) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace+".selectFileByStoredName", storedNm);
	}

}
