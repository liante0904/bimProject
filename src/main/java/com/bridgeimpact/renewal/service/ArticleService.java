package com.bridgeimpact.renewal.service;

import java.util.HashMap;
import java.util.List;

import com.bridgeimpact.renewal.dto.ArticleVO;

public interface ArticleService {

    public List<ArticleVO> selectAllArticle() throws Exception;

    public ArticleVO selectArticleByIndex(int index) throws Exception;
    
    public int selectTotalCntByArticle(HashMap<String, Object> paramMap) throws Exception;

    public List<ArticleVO> selectArticleByBoardName(String id) throws Exception;

    public int insertArticle(ArticleVO board) throws Exception;

	public void editArticle(ArticleVO board)throws Exception;

	public void deleteArticle(ArticleVO board) throws Exception;

	public void increseHitCntByIndex(int index) throws Exception;

	public List<ArticleVO> selectArticleByPage(HashMap<String, Object> paramMap) throws Exception;

	public List<ArticleVO> selectArticleByKeyword(HashMap<String, Object> paramMap) throws Exception;

	public Boolean checkValidateArticleByIdx(int num) throws Exception;



}
