package com.bridgeimpact.renewal.common;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bridgeimpact.renewal.service.ArticleService;

public class PageUtil {

	private int totalArticleCnt;
	private final int displayArticleCnt = 10;
	private int displayPageCnt;
	
	
    private static final Logger logger = LoggerFactory.getLogger(PageUtil.class);
    
	public int getTotalArticleCntByBoardName(ArticleService articleService,String board_name) {
		try {
			 totalArticleCnt = articleService.selectTotalCntByArticle(board_name);
	//		 totalArticleCnt = sqlSession.selectOne(Namespace+".selectTotalCntByArticle", board_name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (totalArticleCnt != 0) {
			displayPageCnt = totalArticleCnt / displayArticleCnt;
			
			if (totalArticleCnt % displayArticleCnt != 0) {
				displayPageCnt = displayPageCnt + 1;
			}
		}
			return totalArticleCnt;
	}


	public int getDisplayPageCnt() {
		return displayPageCnt;
	}


}
