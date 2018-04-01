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
	public List<ArticleVO> selectArticleByboardId(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+".selectArticleByboardId", id);
	}

	@Override
	public int insertArticle(ArticleVO board) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert(Namespace+".insertArticle", board);
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
	public int selectTotalCntByArticle(HashMap<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace+".selectTotalCntByArticle", paramMap);
	}

	@Override
	public List<ArticleVO> selectArticleByPage(HashMap<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		
		return sqlSession.selectList(Namespace+".selectArticleByPage",paramMap);
	}

	@Override
	public List<ArticleVO> selectArticleByKeyword(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+".selectArticleByKeyword", paramMap);
	}
}
