package com.bridgeimpact.renewal.dao;

import java.util.HashMap;
import java.util.List;

import com.bridgeimpact.renewal.dto.ArticleVO;

public interface ArticleDAO {

	List<ArticleVO> selectAllArticle() throws Exception;
	
	ArticleVO selectArticleByIndex(int index) throws Exception;
	
	List<ArticleVO> selectArticleByboardId(String id) throws Exception;
	
	int insertArticle(ArticleVO board) throws Exception;

	int updateArticle(ArticleVO board) throws Exception;

	void deleteArticle(ArticleVO board) throws Exception;

	void updateHitCntByIndex(int index) throws Exception;

	int selectTotalCntByArticle(HashMap<String, Object> paramMap)throws Exception;

	List<ArticleVO> selectArticleByPage(HashMap<String, Object> paramMap)throws Exception;

	List<ArticleVO> selectArticleByRecent(int articleCnt, String boardId) throws Exception ;

}
