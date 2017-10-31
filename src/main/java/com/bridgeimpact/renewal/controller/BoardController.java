package com.bridgeimpact.renewal.controller;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bridgeimpact.renewal.dto.ArticleVO;
import com.bridgeimpact.renewal.dto.BoardVO;
import com.bridgeimpact.renewal.dto.CommentVO;
import com.bridgeimpact.renewal.dto.MemberVO;
import com.bridgeimpact.renewal.service.ArticleService;
import com.bridgeimpact.renewal.service.BoardService;
import com.bridgeimpact.renewal.service.CommentService;
import com.bridgeimpact.renewal.service.MemberService;

 
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
     * id 파라미터를 이용해 게시판에 접근합니다.
     * 
     * @param model
     * @param id 
     * @param request
     * @param session
     * @return
     */
	@RequestMapping(value="/viewList.bim", method= RequestMethod.GET)
	public String boardView(Model model,String id, HttpServletRequest request,HttpSession session){
	
		List<ArticleVO> articleList = null;
		try {
			articleList = articleService.selectArticleByBoardName(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("articleList", articleList);
		 
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
	 * @param id
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
	 * 게시판 관리 페이지에서 게시판 비공개 ajax 요청
	 * @param model
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/closeBoardAjax.bim",method = RequestMethod.GET,produces = "application/json; charset=utf8")
	@ResponseBody
	public Map<String, String> closeBoardAjax(Model model,String id, HttpServletRequest request,HttpServletResponse response){
		 Map<String, String> resultMap = new HashMap<String, String>();
		 int resultCnt = 0;
		try {
			resultCnt = boardService.closeBoard(id);
			logger.info(">>>>>>>>>>>>>"+String.valueOf(resultCnt));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  String result = "";
		  String resultMsg = "";

		  if ( resultCnt == 1 ){
			   result = "success";
			   resultMsg = "게시판이 비공개 되었습니다..";
			  } else {
			   result = "failure";
			   resultMsg = "게시판 비공개 요청이 실패하였습니다..";
			  }

		  resultMap.put("result", result);
		  resultMap.put("resultMsg", resultMsg);
		    response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		  return resultMap;
	}
	
	
	/***
	 * 게시판 관리 페이지에서 게시판 삭제 ajax 요청
	 * @param model
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/openBoardAjax.bim",method = RequestMethod.GET,produces = "application/json; charset=utf8")
	@ResponseBody
	public Map<String, String> openBoardAjax(Model model,String id, HttpServletRequest request,HttpServletResponse response){
		 Map<String, String> resultMap = new HashMap<String, String>();
		 int resultCnt = 0;
		try {
			resultCnt = boardService.openBoard(id);
			logger.info(">>>>>>>>>>>>>"+String.valueOf(resultCnt));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  String result = "";
		  String resultMsg = "";

		  if ( resultCnt == 1 ){
			   result = "success";
			   resultMsg = "게시판이 공개 되었습니다..";
			  } else {
			   result = "failure";
			   resultMsg = "게시판 공개 요청이 실패하였습니다..";
			  }

		  resultMap.put("result", result);
		  resultMap.put("resultMsg", resultMsg);
		    response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		  return resultMap;
	}
}


