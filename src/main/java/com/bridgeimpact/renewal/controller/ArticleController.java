package com.bridgeimpact.renewal.controller;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.bridgeimpact.renewal.dto.CommentVO;
import com.bridgeimpact.renewal.dto.MemberVO;
import com.bridgeimpact.renewal.service.ArticleService;
import com.bridgeimpact.renewal.service.CommentService;
import com.bridgeimpact.renewal.service.MemberService;

 
/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/board")
public class ArticleController {
    
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    
    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private CommentService commentService;
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    
	/***
	 * 게시판 글 작성,편집  페이지 이동 맵핑
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/writeForm.bim")
	public String writeForm(Model model, HttpServletRequest request){
		return "/board/writeForm";
	}
	@RequestMapping(value="/editForm.bim")
	public String editForm(Model model, HttpServletRequest request,HttpSession session){
		// session.setAttribute(name, value);
		return "/board/editForm";
	}
	 
	
	/***
	 * 게시판에서 사용자가 선택한 글을 보여주는 매핑
	 * @param num 글번호 (idx)
	 * @param model
	 * @param session
	 * @return
	 */

	@RequestMapping(value="/viewArticle.bim", method= RequestMethod.GET)
	public ModelAndView boardView(String id,int num,Model model,HttpSession session){
		String URL = "/board/viewForm";
		int articleIndex = num;
		ModelAndView mav = new ModelAndView(URL);
		ArticleVO selectArticleByIndex = null;
		List<CommentVO> selectCommentByIndex = null;
		try {
			articleService.increseHitCntByIndex(articleIndex);
			selectArticleByIndex = articleService.selectArticleByIndex(articleIndex);
			
			// 게시물 접근여부 판별
			if ("Y".equals(selectArticleByIndex.getBoardDelGb())) { // 게시판 비공개 여부

				URL = "redirect:/board/viewList.bim?id="+id;
				mav.setViewName(URL);
				System.out.println("★★★★★>>> 게시판 비공개 시 ");
				return mav;
				
			}else if ("Y".equals(selectArticleByIndex.getDelGb())) { // 게시글 삭제 여부
				URL = "redirect:/board/viewList.bim?id="+id;
				mav.setViewName(URL);
				System.out.println("★★★★★>>> 게시글 삭제시 ");
				return mav;
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			selectCommentByIndex = commentService.selectCommentByIndex(articleIndex);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.addObject("article" , selectArticleByIndex);
		mav.addObject("commentList" , selectCommentByIndex);
		session.setAttribute("articleInfo", selectArticleByIndex);
		
		return mav;
	}
	
	
	/***
	 * 사용자의 글쓰기 요청을 받아 DB에 처리
	 * @param model
	 * @param article
	 * @param request
	 * @param session
	 * @return
	 */
	
	@RequestMapping(value="/writeArticle.bim")
	@ResponseBody
	public Map<String, String> boardWrite(Model model,ArticleVO article, HttpServletRequest request,HttpSession session){
		Map<String, String> resultMap = new HashMap<String, String>();
		logger.info("글 제목 : "+ article.getTitle() + "\t 글내용 : " + article.getContents() );
		System.out.println(">>>>>>>>>>>>>>"+article.getBoardName());
		MemberVO loginMember = (MemberVO)session.getAttribute("loginInfo");
		article.setWriteId(loginMember.getId());
		try {
			articleService.insertArticle(article);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = "success";
		resultMap.put("result", result);
		return resultMap;
	}
	
	/***
	 * 사용자의 글수정 요청을 받아 DB에 처리
	 * @param model
	 * @param article
	 * @param request
	 * @param session
	 * @return
	 */
	
	@RequestMapping(value="/editArticleAjax.bim")
	@ResponseBody
	public Map<String, String> boardEdit(Model model,ArticleVO article, HttpServletRequest request,HttpSession session){
		logger.info("글 제목 : "+ article.getTitle() + "\t 글내용 : " + article.getContents() );
		Map<String, String> resultMap = new HashMap<String, String>();
		ArticleVO sessionBoard = (ArticleVO) session.getAttribute("articleInfo");
		article.setIdx(sessionBoard.getIdx());
		System.out.println("===========>>>"+sessionBoard.getTitle());
		try {
			articleService.editArticle(article);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = "success";
		resultMap.put("result", result);
		return resultMap;
	}
	
	/***
	 * 사용자의 글삭제 요청을 받아 DB에 처리
	 * @param model
	 * @param article
	 * @param request
	 * @param session
	 * @return
	 */
	
	@RequestMapping(value="/deleteArticle.bim")
	public ModelAndView boardDelete(String id,int num,Model model,ArticleVO article, HttpServletRequest request,HttpSession session){
		ModelAndView mav = new ModelAndView("redirect:/board/viewList.bim?id="+id);
		ArticleVO sessionBoard = (ArticleVO) session.getAttribute("articleInfo");
		MemberVO sessionMember = (MemberVO) session.getAttribute("loginInfo");
		article.setIdx(num);
		System.out.println("세션 board : "+sessionBoard.getWriteId() +"세션아이디 : " +sessionMember.getId());
		
		if (!sessionBoard.getWriteId().equals(sessionMember.getId())) { // 잘못된 접근 (로그인 사용자와 글 작성자가 다른 요청)
			return mav;
		}else {
			article.setWriteId(sessionMember.getId());
			try {
				articleService.deleteArticle(article);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return mav;
	}
    
}


