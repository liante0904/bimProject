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
	public List<BoardVO> selectAllBoard(String delGb) {
		return sqlSession.selectList(Namespace+".selectAllBoard", delGb);
	}

	
	@Override
	public List<BoardVO> selectAllBoard() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+".selectAllBoard");
	}

	@Override
	public void insertBoard(BoardVO board) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(Namespace+".insertBoard", board);
	}



	@Override
	public int selectBoardCntById(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace+".selectBoardCntById", id);
	}



	@Override
	public int deleteBoardById(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update(Namespace+".deleteBoardById", id);
	}


	@Override
	public int unDeleteBoardById(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update(Namespace+".unDeleteBoardById", id);
	}


	@Override
	public String selectBoardDelgbById(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace+".selectBoardDelgbById", id);
	}


	@Override
	public BoardVO selectBoardById(String id) throws Exception {
		return (BoardVO)sqlSession.selectOne(Namespace+".selectBoardById", id);
		
	}


}
