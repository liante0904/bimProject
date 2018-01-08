package com.bridgeimpact.renewal.dao;

import java.util.List;

import com.bridgeimpact.renewal.common.PageUtil;
import com.bridgeimpact.renewal.dto.ArticleVO;

public interface ArticleDAO {

	public List<ArticleVO> selectAllArticle() throws Exception;
	
	public ArticleVO selectArticleByIndex(int index) throws Exception;
	
	public List<ArticleVO> selectArticleByBoardName(String id) throws Exception;
	
	public void insertArticle(ArticleVO board) throws Exception;

	public void updateArticle(ArticleVO board) throws Exception;

	public void deleteArticle(ArticleVO board) throws Exception;

	public void updateHitCntByIndex(int index) throws Exception;

	public int selectTotalCntByArticle(String board_name)throws Exception;

	public List<ArticleVO> selectArticleByPage(PageUtil pageUtil)throws Exception;

}
