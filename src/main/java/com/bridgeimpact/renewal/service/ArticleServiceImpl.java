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
    private ArticleDAO boardDAO;
    
	
	@Override
	public List<ArticleVO> selectAllArticle() throws Exception {
		// TODO Auto-generated method stub
		return boardDAO.selectAllArticle();
	}
	
	@Override
	public ArticleVO selectArticleByIndex(int index) throws Exception {
		// TODO Auto-generated method stub
		return  boardDAO.selectArticleByIndex(index);
	}

	@Override
	public void insertArticle(ArticleVO board) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.insertArticle(board);
	}

	@Override
	public void editArticle(ArticleVO board) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.updateArticle(board);
	}

	@Override
	public void deleteArticle(ArticleVO board) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.deleteArticle(board);
	}

	@Override
	public void increseHitCntByIndex(int index) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.updateHitCntByIndex(index);
	}

}
