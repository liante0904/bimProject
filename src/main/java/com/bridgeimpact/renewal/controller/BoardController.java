package com.bridgeimpact.renewal.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bridgeimpact.renewal.common.PageUtil;
import com.bridgeimpact.renewal.dto.ArticleVO;
import com.bridgeimpact.renewal.dto.BoardVO;
import com.bridgeimpact.renewal.service.ArticleService;
import com.bridgeimpact.renewal.service.BoardService;

 
/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/board")
public class BoardController {
    
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    
    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private BoardService boardService;
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    
	
    
    /***
     * id(게시판) 파라미터를 이용해 게시판에 접근합니다.
     * 
     * @param model
     * @param id : 게시판 구분자 ID
     * @param request
     * @param session
     * @return
     */
	@RequestMapping(value="/viewList.bim", method= RequestMethod.GET)
	public String boardView(String id,Model model, HttpServletRequest request,HttpSession session){
		
		/***
		 * 게시판 유효성 판별
		 * @param id : 게시판 구분자
		 * @return checkBoardResult : 게시판 유효성 체크결과 value = 0 비공개 게시판, 1 = 유효 게시판
		 */
		int checkBoardResult = 0;
		try {
			checkBoardResult = boardService.checkBoardStatusById(id);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			if (String.valueOf(id) == null || checkBoardResult == 0 ) {
				//TODO 유효하지 않는 게시판 접근시 로직
				return "main/mainForm";
			}
		}

		
		/***
		 * 게시판의 페이징 세팅 
		 */
		
		// 이용자의 요청 페이지 세팅
		PageUtil pageUtil = new PageUtil(request,articleService);
		
		
		if (pageUtil.getCurrentPage() > pageUtil.getTotalPageCnt()) {
			//TODO 유효하지 않은 페이지 범위 접근시
		String url = "redirect:/board/viewList.bim?id=" + id + "&page=" + pageUtil.getTotalPageCnt();
			return 	url;
		}
		/***
		 * 요청 게시판의 게시글 세팅
		 */
		
		List<ArticleVO> articleList = null;
		
		// 계산된 게시글 가져오기		
		try {
			articleList = articleService.selectArticleByPage(pageUtil.getParamMap());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		System.out.println("출력될 게시글 수 : " + pageUtil.getDisplayArticleCnt());
		
		model.addAttribute("articleList", articleList);
		model.addAttribute("pageUtil", pageUtil);

		logger.info(id+"게시판 요청");
		return "board/articleList";
	}
	
	
	
	/***
	 * 게시판 생성 페이지에서 게시판 생성 요청
	 * @param model
	 * @param board
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/insertBoardAjax.bim")
	@ResponseBody
	public Map<String, String> insertBoard(Model model,BoardVO board, HttpServletRequest request,HttpSession session){
		Map<String, String>  resultMap = new HashMap<String, String>();
		String result = "";
		String resultMsg = "";

		try {
			boardService.insertBoard(board);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "failure";
			resultMsg = "게시판 생성이 실패 하였습니다.";
		}
		   result = "success";
		   resultMsg = "게시판 생성이 완료 되었습니다.";
		   
		   resultMap.put("result", result);
		   resultMap.put("resultMsg", resultMsg);
		   return resultMap;
	}
	
	
	
	/***
	 * 게시판 추가 페이지에서 게시판 URL 중복체크 ajax 요청
	 * @param model
	 * @param id : 중복확인을 하고자 하는 ID
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/checkBoardIdAjax.bim",method = RequestMethod.GET,produces = "application/json; charset=utf8")
	@ResponseBody
	public Map<String, String> checkBoardId(Model model,String id, HttpServletRequest request,HttpServletResponse response){
		 Map<String, String> resultMap = new HashMap<String, String>();
		 int resultCnt = 0;
		try {
			resultCnt = boardService.checkBoardId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  String result = "";
		  String resultMsg = "";

		  if ( resultCnt == 0 ){
			   result = "success";
			   resultMsg = "사용가능한 URL입니다.";
			  } else {
			   result = "failure";
			   resultMsg = "이미 사용중인 URL입니다.";
			  }

		  resultMap.put("result", result);
		  resultMap.put("resultMsg", resultMsg);
		    response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		  return resultMap;
	}
	
	/***
	 *  관리자페이지의 게시판 관리 요청 
	 *  (게시판 공개 <-> 비공개 ajax)
	 * @param model
	 * @param id : 게시판 구분 ID
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/toggleBoardAjax.bim",method = RequestMethod.GET,produces = "application/json; charset=utf8")
	@ResponseBody
	public Map<String, String> toggleBoardAjax(Model model,String id, HttpServletRequest request,HttpServletResponse response){
		 Map<String, String> resultMap = new HashMap<String, String>();
		 
		 /**
		  * resultState 상태 값
		  * 0: 에러(DB접근 실패), 1: 공개로 변경 성공 , 2: 비공개로 변경 성공
		  */
		 int resultState = 0;
		try {
			//resultCnt = boardService.openBoard(id);
			resultState = boardService.toogleBoard(id);
			
			logger.info(">>>>>>>>>>>>>"+String.valueOf(resultState));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  String result = "";
		  String resultMsg = "";

		  if (resultState == 0) {
			  result = "failure";
			  resultMsg = "게시판 변경을 실패 하였습니다..";
		  }else if ( resultState == 1 ){
			  result = "success";
			  resultMsg = id + "게시판이 공개 되었습니다..";
		  } else {
			  result = "success";
			  resultMsg = id + "게시판이 비공개 되었습니다..";
		  }

		  resultMap.put("result", result);
		  resultMap.put("resultMsg", resultMsg);
		  response.setContentType("text/plain");
		  response.setCharacterEncoding("UTF-8");
		  return resultMap;
	}
	

}


