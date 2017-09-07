package com.bridgeimpact.renewal.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bridgeimpact.renewal.dto.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.bridgeimpact.renewal.boardSQL";
    
    
	@Override
	public List<BoardVO> selectAllBoard() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+".selectAllBoard");
	}
	
	@Override
	public BoardVO selectBoardByIndex(int index) throws Exception {
		// TODO Auto-generated method stub
		return (BoardVO)sqlSession.selectOne(Namespace+".selectBoardByIndex", index);
	}

	@Override
	public void insertBoard(BoardVO board) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(Namespace+".insertBoard", board);
	}

	@Override
	public void updateBoard(BoardVO board) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace+".updateBoard", board);
	}

	@Override
	public void deleteBoard(BoardVO board) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(Namespace+".deleteBoard", board);
	}

	@Override
	public void updateHitCntByIndex(int index) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace+".updateHitCntByIndex", index);
		
	}



}
