package com.bridgeimpact.renewal.service;

import java.util.List;


import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeimpact.renewal.dao.ArticleDAO;
import com.bridgeimpact.renewal.dto.ArticleVO;


@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;
    
	
	@Override
	public List<ArticleVO> selectAllArticle() throws Exception {
		// TODO Auto-generated method stub
		return articleDAO.selectAllArticle();
	}
	
	@Override
	public ArticleVO selectArticleByIndex(int index) throws Exception {
		// TODO Auto-generated method stub
		return  articleDAO.selectArticleByIndex(index);
	}

	@Override
	public List<ArticleVO> selectArticleByBoardName(String id) throws Exception {
		// TODO Auto-generated method stub
		return articleDAO.selectArticleByBoardName(id);
	}
	
	@Override
	public void insertArticle(ArticleVO board) throws Exception {
		// TODO Auto-generated method stub
		articleDAO.insertArticle(board);
	}

	@Override
	public void editArticle(ArticleVO board) throws Exception {
		// TODO Auto-generated method stub
		articleDAO.updateArticle(board);
	}

	@Override
	public void deleteArticle(ArticleVO board) throws Exception {
		// TODO Auto-generated method stub
		articleDAO.deleteArticle(board);
	}

	@Override
	public void increseHitCntByIndex(int index) throws Exception {
		// TODO Auto-generated method stub
		articleDAO.updateHitCntByIndex(index);
	}

	@Override
	public int selectTotalCntByArticle(String board_name) throws Exception {
		// TODO Auto-generated method stub
		return articleDAO.selectTotalCntByArticle(board_name);
	}


}
