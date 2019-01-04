package com.bridgeimpact.renewal.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bridgeimpact.renewal.dto.ArticleVO;

@Repository
public class ArticleDAOImpl implements ArticleDAO {

    @Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.bridgeimpact.renewal.articleSQL";
    
    
	@Override
	public List<ArticleVO> selectAllArticle() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+".selectAllArticle");
	}
	
	@Override
	public ArticleVO selectArticleByIndex(int index) {
		// TODO Auto-generated method stub
		return (ArticleVO)sqlSession.selectOne(Namespace+".selectArticleByIndex", index);
	}

	@Override
	public List<ArticleVO> selectArticleByboardId(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+".selectArticleByboardId", id);
	}

	@Override
	public int insertArticle(ArticleVO board) {
		// TODO Auto-generated method stub
		return sqlSession.insert(Namespace+".insertArticle", board);
	}

	@Override
	public int updateArticle(ArticleVO board) {
		return sqlSession.update(Namespace+".updateArticle", board);
	}

	@Override
	public void deleteArticle(ArticleVO board) {
		// TODO Auto-generated method stub
		sqlSession.delete(Namespace+".deleteArticle", board);
	}

	@Override
	public void updateHitCntByIndex(int index) {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace+".updateHitCntByIndex", index);
		
	}

	@Override
	public int selectTotalCntByArticle(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace+".selectTotalCntByArticle", paramMap);
	}

	@Override
	public List<ArticleVO> selectArticleByPage(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
		return sqlSession.selectList(Namespace+".selectArticleByPage",paramMap);
	}

	@Override
	public List<ArticleVO> selectArticleByRecent(int articleCnt, String boardId) {
		// TODO Auto-generated method stub
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("articleCnt", 5);
		paramMap.put("boardId", boardId);
		return sqlSession.selectList(Namespace+".selectArticleByRecent", paramMap);
		
	}
}
