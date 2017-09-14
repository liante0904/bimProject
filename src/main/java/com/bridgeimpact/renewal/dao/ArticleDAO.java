package com.bridgeimpact.renewal.dao;

import java.util.List;

import com.bridgeimpact.renewal.dto.ArticleVO;

public interface ArticleDAO {

	List<ArticleVO> selectAllArticle() throws Exception;
	
	ArticleVO selectArticleByIndex(int index) throws Exception;

	void insertArticle(ArticleVO board) throws Exception;

	void updateArticle(ArticleVO board) throws Exception;

	void deleteArticle(ArticleVO board) throws Exception;

	void updateHitCntByIndex(int index) throws Exception;



}
