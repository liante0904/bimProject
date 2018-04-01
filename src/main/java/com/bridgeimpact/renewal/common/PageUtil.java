package com.bridgeimpact.renewal.common;


import java.util.HashMap;

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
	private String boardId;
	private HashMap<String, Object> paramMap = new HashMap<String, Object>();
	


	private static final Logger logger = LoggerFactory.getLogger(PageUtil.class);


	private void init(HttpServletRequest request, ArticleService articleService) {
		
		// 페이지 요청 처리
		if (request.getParameter("page") == null || "".equals(request.getParameter("page"))) {
			// 페이지 파리미터가 없을때
			this.setCurrentPage(0);
		}else {
			int page = Integer.parseInt(request.getParameter("page").toString()) - 1;
			if (page < 0 ) {
				page = 0;
			}
			this.setCurrentPage(page);
		}
		

		
		
	}

	/***
	 * pageUtil 생성자 게시판 요청시 페이지 처리를 합니다.
	 * 사용자의 request를 이용해 검색, 글 조회를 우선 판단한 뒤
	 * 조건에 맞는 글을 쿼리하여 페이징 처리합니다. 
	 * @param request
	 * @param articleService
	 */
	public PageUtil(HttpServletRequest request, ArticleService articleService) {
		/***
		 * 사용자의 요청 페이지수 계산 구간
		 */
		this.init(request, articleService);

		
		/***
		 * 사용자가 요청한 조건의 게시글을 Map으로 구현
		 */
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("boardId", request.getParameter("id"));
		paramMap.put("searchType", request.getParameter("searchType"));
		paramMap.put("searchKeyword", request.getParameter("searchKeyword"));

		this.setboardId(String.valueOf(paramMap.get("boardId")));
		this.setTotalArticleCnt(articleService,request,paramMap);
		this.setTotalPageCnt();
		this.setPageRangeCnt();
		
		paramMap.put("startArticleCnt", this.getStartArticleCnt());
		paramMap.put("displayArticleCnt", this.getDisplayArticleCnt());
		this.setParamMap(paramMap);
		System.out.println("현재 페이지(실제 페이지보다 -1) : " + this.getCurrentPage());
		

		System.out.println(boardId +"게시판 글 수 : " + this.getTotalArticleCnt());
		System.out.println("게시판의 총  페이지 갯 수 : "+ this.getTotalPageCnt());


	}




	/***
	 * 사용자가 요청한 게시글의 갯수를 구합니다.
	 * @param articleService
	 * @param request
	 * @param paramMap : 게시판 구분, 검색 타입, 검색 키워드를 포함합니다.
	 */
	private void setTotalArticleCnt(ArticleService articleService, HttpServletRequest request, HashMap<String, Object> paramMap) {
		try {
			totalArticleCnt = articleService.selectTotalCntByArticle(paramMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String getboardId() {
		return boardId;
	}


	public void setboardId(String boardId) {
		this.boardId = boardId;
	}


	public int getPageRangeCnt() {
		return pageRangeCnt;
	}


	public void setPageRangeCnt() {
		// 현재 페이지의 표시될 페이지의 범위를 계산
		System.out.println("pageRangeCnt: 구간에서 " +currentPage);
			 if (currentPage + 1 < 11) { //현재 페이지가 첫페이지 일 경우(1~10페이지)
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


	public int getTotalArticleCnt() {
		return totalArticleCnt;
	}



	public int getTotalPageCnt() {
		return totalPageCnt;
	}

	public void setTotalPageCnt() {
		// 총 페이지수 계산
		if (totalArticleCnt != 0) {
			totalPageCnt = totalArticleCnt / displayArticleCnt;
			
			if (totalArticleCnt % displayArticleCnt != 0) {
				totalPageCnt = totalPageCnt + 1;
			}
		}
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

	
	public HashMap<String, Object> getParamMap() {
		return paramMap;
	}


	public void setParamMap(HashMap<String, Object> paramMap) {
		this.paramMap = paramMap;
	}



}