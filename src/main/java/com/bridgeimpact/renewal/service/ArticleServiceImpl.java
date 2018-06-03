package com.bridgeimpact.renewal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeimpact.renewal.dao.ArticleDAO;
import com.bridgeimpact.renewal.dao.BoardDAO;
import com.bridgeimpact.renewal.dto.ArticleVO;
import com.bridgeimpact.renewal.dto.BoardVO;


@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    @Autowired
    private BoardDAO boardDAO;
    
	
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
	public List<ArticleVO> selectArticleByboardId(String id) throws Exception {
		// TODO Auto-generated method stub
		return articleDAO.selectArticleByboardId(id);
	}
	
	@Override
	public int insertArticle(ArticleVO board) throws Exception {
		return  articleDAO.insertArticle(board);
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
	public int selectTotalCntByArticle(HashMap<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return articleDAO.selectTotalCntByArticle(paramMap);
	}

	@Override
	public List<ArticleVO> selectArticleByPage(HashMap<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return articleDAO.selectArticleByPage(paramMap);
	}

	@Override
	public List<ArticleVO> selectArticleByKeyword(HashMap<String, Object> paramMap)  throws Exception {
		// TODO Auto-generated method stub
		return articleDAO.selectArticleByKeyword(paramMap);
	}

	@Override
	public Boolean checkValidateArticleByIdx(int num) throws Exception {
		// TODO Auto-generated method stub
		ArticleVO article = articleDAO.selectArticleByIndex(num);
		String boardDelgb = boardDAO.selectBoardDelgbById(article.getBoardId());
		if ("Y".equals(article.getDelGb()) || "Y".equals(boardDelgb)) {
			return false;
		}
		return true;
	}

	@Override
	public List<ArticleVO> selectMainArticleList(List<BoardVO> boardList) throws Exception {
		//TODO articleCnt should be static parameter
		int articleCnt = 5;
    	List<ArticleVO> resultList = new ArrayList<ArticleVO>();
    	resultList.addAll(articleDAO.selectArticleByRecent(articleCnt,boardList.get(0).getId()));
    	resultList.addAll(articleDAO.selectArticleByRecent(articleCnt,boardList.get(1).getId()));
    	resultList.addAll(articleDAO.selectArticleByRecent(articleCnt,boardList.get(2).getId()));
    	resultList.addAll(articleDAO.selectArticleByRecent(articleCnt,boardList.get(3).getId()));
		System.out.println("map 테스트 : " + resultList);
    	return resultList;
	}


}
