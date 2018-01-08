package com.bridgeimpact.renewal.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bridgeimpact.renewal.dto.ArticleVO;

@Repository
public class ArticleDAOImpl implements ArticleDAO {

    @Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.bridgeimpact.renewal.articleSQL";
    
    
	@Override
	public List<ArticleVO> selectAllArticle() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+".selectAllArticle");
	}
	
	@Override
	public ArticleVO selectArticleByIndex(int index) throws Exception {
		// TODO Auto-generated method stub
		return (ArticleVO)sqlSession.selectOne(Namespace+".selectArticleByIndex", index);
	}

	@Override
	public List<ArticleVO> selectArticleByBoardName(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+".selectArticleByBoardName", id);
	}

	@Override
	public void insertArticle(ArticleVO board) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(Namespace+".insertArticle", board);
	}

	@Override
	public void updateArticle(ArticleVO board) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace+".updateArticle", board);
	}

	@Override
	public void deleteArticle(ArticleVO board) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(Namespace+".deleteArticle", board);
	}

	@Override
	public void updateHitCntByIndex(int index) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace+".updateHitCntByIndex", index);
		
	}

	@Override
	public int selectTotalCntByArticle(String board_name) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace+".selectTotalCntByArticle", board_name);
	}

	@Override
	public List<ArticleVO> selectTotalCntByPage(int currentPage) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+".selectTotalCntByPage");
	}
}
