package com.bridgeimpact.renewal.service;

import java.util.HashMap;
import java.util.List;

import com.bridgeimpact.renewal.dto.ArticleVO;
import com.bridgeimpact.renewal.dto.BoardVO;

import javax.servlet.http.HttpServletRequest;

public interface ArticleService {

    public List<ArticleVO> selectAllArticle() throws Exception;

    public ArticleVO selectArticleByIndex(int index) throws Exception;
    
    public int selectTotalCntByArticle(HashMap<String, Object> paramMap) throws Exception;

    public List<ArticleVO> selectArticleByboardId(String id) throws Exception;

    public int writeArticle(HttpServletRequest request, ArticleVO board) throws Exception;

	public void editArticle(ArticleVO board)throws Exception;

	public void deleteArticle(ArticleVO board) throws Exception;

	public void increaseHitCntByIndex(int index) throws Exception;

	public List<ArticleVO> selectArticleByPage(HashMap<String, Object> paramMap) throws Exception;

	public Boolean checkValidateArticleByIdx(int num) throws Exception;

	public List<ArticleVO> selectMainArticleList(List<BoardVO> boardList) throws Exception;



}
