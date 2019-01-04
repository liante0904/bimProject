package com.bridgeimpact.renewal.service;

import java.util.HashMap;
import java.util.List;

import com.bridgeimpact.renewal.dto.ArticleVO;
import com.bridgeimpact.renewal.dto.BoardVO;

import javax.servlet.http.HttpServletRequest;

public interface ArticleService {

    List<ArticleVO> selectAllArticle() throws Exception;

    ArticleVO selectArticleByIndex(int index) throws Exception;
    
    int selectTotalCntByArticle(HashMap<String, Object> paramMap) throws Exception;

    List<ArticleVO> selectArticleByboardId(String id) throws Exception;

    int writeArticle(HttpServletRequest request, ArticleVO board) throws Exception;

	int editArticle(HttpServletRequest request, ArticleVO article)throws Exception;

	void deleteArticle(ArticleVO board) throws Exception;

	void increaseHitCntByIndex(int index) throws Exception;

	List<ArticleVO> selectArticleByPage(HashMap<String, Object> paramMap) throws Exception;

	Boolean checkValidateArticleByIdx(int num) throws Exception;

	List<ArticleVO> selectMainArticleList(List<BoardVO> boardList) throws Exception;



}
