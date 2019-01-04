package com.bridgeimpact.renewal.common;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bridgeimpact.renewal.service.ArticleService;

public class PageUtil {

	private final int displayArticleCnt = 10;
	// 보여질 게시글 갯수
	private final int displayPageCnt = 5;
	// 보여질 페이지 갯수 
	private int totalArticleCnt;
	private int currentPage;
	private int totalPageCnt;
	private int startArticleCnt;
	private int pageRangeCnt;
	private String boardId;
	private HashMap<String, Object> paramMap = new HashMap<String, Object>();
	

	private static final Logger logger = LoggerFactory.getLogger(PageUtil.class);

	/***
	 * pageUtil 생성자
	 * 게시판 요청시 페이지 처리를 합니다.
	 * 사용자의 request를 이용해 요청타입을 구분 (검색, 글 조회)를 판별한 뒤
	 * 조건에 맞는 글을 쿼리하여 페이징 처리합니다. 
	 * @param request
	 * @param articleService
	 */
	public PageUtil(HttpServletRequest request, ArticleService articleService) {
		
		/***
		 * 사용자의 요청 페이지수 계산
		 */
		this.setCurrentPage(request);
		
		/***
		 * 사용자의 요청 (조회 & 검색) Map 으로 추상화
		 */
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("boardId", request.getParameter("id"));
		paramMap.put("searchType", request.getParameter("searchType"));
		paramMap.put("searchKeyword", request.getParameter("searchKeyword"));

		/***
		 * 페이징을 위한 초기화 세팅 
		 * 1. 요청 게시판 ID
		 * 2. 해당 게시판의 게시글 갯수
		 * 3. 해당 게시판의 총 페이지 수
		 * 4. 출력될 게시판 범위
		 * 5. 페이징 게시글 범위 세팅
		 */
		this.setboardId(String.valueOf(paramMap.get("boardId")));
		this.setTotalArticleCnt(articleService,request,paramMap);
		this.setTotalPageCnt();
		this.setPageRangeCnt();
		this.setStartArticleCnt();

		/***
		 * 페이징에 필요한 변수를 Map에 초기화 합니다.
		 * 1. 요청 페이지의 시작 글 수
		 * 2. 페이지에 보여질 게시글 수
		 */
		paramMap.put("startArticleCnt", this.getStartArticleCnt());
		paramMap.put("displayArticleCnt", this.getDisplayArticleCnt());
		this.setParamMap(paramMap);
		logger.info("현재 페이지(실제 페이지보다 -1) : " + this.getCurrentPage());
		logger.info(boardId +"게시판 글 수 : " + this.getTotalArticleCnt());
		logger.info("게시판의 총  페이지 갯 수 : "+ this.getTotalPageCnt());
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


	private void setboardId(String boardId) {
		this.boardId = boardId;
	}


	public int getPageRangeCnt() {
		return pageRangeCnt;
	}


	private void setPageRangeCnt() {
		// 현재 페이지의 표시될 페이지의 범위를 계산
		logger.info("pageRangeCnt: 구간에서 " +currentPage);
			 if (currentPage + 1 <  displayPageCnt + 1) { //현재 페이지가 첫페이지 일 경우(1~displayPageCnt페이지)
				pageRangeCnt = 0;
			}else { //11페이지 이상일 경우
				pageRangeCnt = (currentPage / displayPageCnt) * displayPageCnt;
				if (currentPage + 1 == pageRangeCnt) {
					pageRangeCnt = pageRangeCnt - displayPageCnt;
					logger.info("current랑 rangeCnt랑 같으면 - displayPageCnt" + pageRangeCnt);
				}
			}

		logger.info("pageRangeCnt: "+pageRangeCnt);
	}

	public int getStartArticleCnt() {
		return startArticleCnt;
	}

	/***
	 * 해당 페이지의 보여질 글 인덱스를 계산합니다.
	 */
	private void setStartArticleCnt() {
		// 첫페이지 일 경우
		if (currentPage == 0) {
			this.startArticleCnt = currentPage;
		}else {
			/***
			 * 첫 페이지가 아닌 경우 해당 페이지의 첫 글 인덱스를 계산합니다.
			 * MySQL, MariaDB의 LIMIT 기능을 이용해 해당 페이지에 보여질 게시글 범위를 계산합니다.
			 * 만약 1페이지에 게시글이 10개 보여지며.. 사용자가 3페이지를 요청했을 경우
			 * SELECT 쿼리에 LIMIT 30, 10 를 붙여서 요청할때 사용됩니다.
			 */

			this.startArticleCnt =  displayArticleCnt * currentPage;
		}
	}

	public int getTotalArticleCnt() {
		return totalArticleCnt;
	}

	public int getTotalPageCnt() {
		return totalPageCnt;
	}

	private void setTotalPageCnt() {
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

	

	public int getDisplayPageCnt() {
		return displayPageCnt;
	}




	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * page 파라미터를 이용해 현재 페이지 수를 판단합니다.
	 * TODO 파라미터 로직은 인터셉터에서 판별하는 것으로 옮겨질 예정입니다.
	 * */
	private void setCurrentPage(HttpServletRequest request) {
		// page 파라미터를 판별합니다.
		if (request.getParameter("page") == null || "".equals(request.getParameter("page"))) {
			// 페이지 파리미터가 없을때
			currentPage = 0;
		}else {
			/**
			 *  페이지 파라미터를 0으로 받았을때 예외처리
			 *  사용자 임의로 0 혹은 음수를 요청할 때 첫 페이지를 반환합니다.
			 * */
			currentPage = Integer.parseInt(request.getParameter("page")) - 1;
			if (currentPage <= 0 ) currentPage = 0;
		}
	}

	
	public HashMap<String, Object> getParamMap() {
		return paramMap;
	}

	private void setParamMap(HashMap<String, Object> paramMap) {
		this.paramMap = paramMap;
	}



}