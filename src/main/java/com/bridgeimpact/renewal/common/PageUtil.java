package com.bridgeimpact.renewal.common;


import javax.servlet.http.HttpServletRequest;

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
	private String boardName;
	
	
	
	


	public PageUtil(HttpServletRequest request, ArticleService articleService) {
		boardName = String.valueOf(request.getParameter("id"));
		if (request.getParameter("page") == null || "".equals(request.getParameter("page"))) {
			// 페이지 파리미터가 없을때
			this.setCurrentPage(0);
		}else {
			int page = Integer.parseInt(request.getParameter("page").toString()) - 1;
			this.setCurrentPage(page);
		}
		System.out.println("현재 페이지(실제 페이지보다 -1) : " + this.getCurrentPage());
		this.setTotalArticleCnt(articleService,boardName);
		

		System.out.println(boardName +"게시판 글 수 : " + this.getTotalArticleCnt());
		System.out.println("게시판의 총  페이지 갯 수 : "+ this.getTotalPageCnt());


	}


	public String getboardName() {
		return boardName;
	}


	public void setboardName(String boardName) {
		this.boardName = boardName;
	}


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
		
		if (currentPage == 0) {
			this.startArticleCnt = currentPage;
		}else {
			this.startArticleCnt =  displayArticleCnt * currentPage;
		}
	}


	private static final Logger logger = LoggerFactory.getLogger(PageUtil.class);
    

	
	
    /***
     * 현재 게시판의 총 게시물 갯수를 요청하고, 총 페이지 수를 계산합니다.
     * @param articleService	DAO접근을 위한 객체
     * @param boardName		현재 접근하는 게시판
     */
    
	public void setTotalArticleCnt(ArticleService articleService,String boardName) {
		this.boardName = boardName;
		// 게시글 총 갯수 조회
		try {
			 totalArticleCnt = articleService.selectTotalCntByArticle(boardName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 총 페이지수 계산
		if (totalArticleCnt != 0) {
			totalPageCnt = totalArticleCnt / displayArticleCnt;
			
			if (totalArticleCnt % displayArticleCnt != 0) {
				totalPageCnt = totalPageCnt + 1;
			}
		}
		
		// 현재 페이지의 표시될 페이지의 범위를 계산
		
		 if (currentPage != 0) {
			 if (currentPage + 1 < 10) { //현재 페이지가 첫페이지 일 경우(1~10페이지)
				pageRangeCnt = 0;
			}else { //11페이지 이상일 경우
				pageRangeCnt = (currentPage / 10) * 10;
				if (currentPage + 1 == pageRangeCnt) {
					
					pageRangeCnt = pageRangeCnt - 10;
					System.out.println("current랑 rangeCnt랑 같으면 -10" + pageRangeCnt);
				}
				
			}
			 System.out.println("pageRangeCnt: "+pageRangeCnt);
		}
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
		this.setStartArticleCnt();
	}


	public void setTotalArticleCnt(int totalArticleCnt) {
		this.totalArticleCnt = totalArticleCnt;
	}

	



}