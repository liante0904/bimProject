package com.bridgeimpact.renewal.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bridgeimpact.renewal.dto.ArticleVO;
import com.bridgeimpact.renewal.dto.CommentVO;

@Repository
public class CommentDAOImpl implements CommentDAO {

    @Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.bridgeimpact.renewal.commentSQL";
    
    
	@Override
	public List<CommentVO> selectAllComment() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+".selectAllComment");
	}
	
	@Override
	public List<CommentVO> selectCommentByIndex(int index) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+".selectCommentByIndex", index);
	}

	@Override
	public void insertComment(CommentVO comment) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(Namespace+".insertComment", comment);
	}

	@Override
	public void updateComment(CommentVO comment) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace+".updateComment", comment);
	}

	@Override
	public void deleteComment(CommentVO comment) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(Namespace+".deleteComment", comment);
	}

	@Override
	public void updateHitCntByIndex(int index) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace+".updateHitCntByIndex", index);
		
	}



}
