package com.bridgeimpact.renewal.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bridgeimpact.renewal.service.ArticleService;

public class PageUtil {

	private int totalArticleCnt;
	private final int displayArticleCnt = 2;
	private int currentPage;
	private int totalPageCnt;
	private int startArticleCnt;
	private int pageRangeCnt;
	
	
	
	
    public int getPageRangeCnt() {
		return pageRangeCnt;
	}


	public void setPageRangeCnt(int pageRangeCnt) {
		this.pageRangeCnt = pageRangeCnt;
	}




	public int getStartArticleCnt() {
		return startArticleCnt;
	}


	public void setStartArticleCnt() {
		
		if (currentPage != 0) {
			startArticleCnt = currentPage * displayArticleCnt;
		}
		this.startArticleCnt = startArticleCnt;
	}


	private static final Logger logger = LoggerFactory.getLogger(PageUtil.class);
    

	
	
    /***
     * 현재 게시판의 총 게시물 갯수를 요청하고, 총 페이지 수를 계산합니다.
     * @param articleService	DAO접근을 위한 객체
     * @param board_name		현재 접근하는 게시판
     */
    
	public void setTotalArticleCnt(ArticleService articleService,String board_name) {
		// 게시글 총 갯수 조회
		try {
			 totalArticleCnt = articleService.selectTotalCntByArticle(board_name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 총 페이지수 게산
		if (totalArticleCnt != 0) {
			totalPageCnt = totalArticleCnt / displayArticleCnt;
			
			if (totalArticleCnt % displayArticleCnt != 0) {
				totalPageCnt = totalPageCnt + 1;
			}
		}
		
		// 현재 페이지의 표시될 페이지의 범위를 계산
		
		 if (currentPage != 0) {
			
			 if (currentPage < 11) { //현재 페이지가 첫페이지 일 경우
				pageRangeCnt = 0;
				 
			}else { //이외의 페이지일 경우
				pageRangeCnt = (currentPage / 10) * 10;
			}
			 System.out.println("pageRangeCnt: "+pageRangeCnt);
		}
		 1~10 
	}


	public int getTotalArticleCnt() {
		
		return totalArticleCnt;
	}



	public int getTotalPageCnt() {
		return totalPageCnt;
	}

	public void setTotalPageCnt(int totalPageCnt) {
		this.totalPageCnt = totalPageCnt;
	}

	public int getDisplayArticleCnt() {
		return displayArticleCnt;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public void setTotalArticleCnt(int totalArticleCnt) {
		this.totalArticleCnt = totalArticleCnt;
	}

	



}
