package com.bridgeimpact.renewal.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bridgeimpact.renewal.dto.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.bridgeimpact.renewal.boardSQL";
    
    
	
	@Override
	public List<BoardVO> selectAllBoard() {
		return sqlSession.selectList(Namespace+".selectAllBoard");
	}



	@Override
	public void insertBoard(BoardVO board) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(Namespace+".insertBoard", board);
	}



	@Override
	public int selectBoardById(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace+".selectBoardById", id);
	}

}
