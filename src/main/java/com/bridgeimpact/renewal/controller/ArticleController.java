package com.bridgeimpact.renewal.controller;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.List;
import java.util.Locale;
 
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
	public ModelAndView boardView(int num,Model model,HttpSession session){
		int articleIndex = num;
		ModelAndView mav = new ModelAndView("/board/viewForm");
		ArticleVO selectArticleByIndex = null;
		List<CommentVO> selectCommentByIndex = null;
		try {
			articleService.increseHitCntByIndex(articleIndex);
			selectArticleByIndex = articleService.selectArticleByIndex(articleIndex);
			
			
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
	 * 사용자의 글쓰기 요청을 받아 DB에 처리하는 매핑
	 * @param model
	 * @param article
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/writeArticle.bim")
	public ModelAndView boardWrite(Model model,ArticleVO article, HttpServletRequest request,HttpSession session){
		logger.info("글 제목 : "+ article.getTitle() + "\t 글내용 : " + article.getContents() );
		MemberVO loginMember = (MemberVO)session.getAttribute("loginInfo");
		article.setWriteId(loginMember.getId());
		ModelAndView mav = new ModelAndView("redirect:/board/viewList.bim");
		try {
			articleService.insertArticle(article);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	
	/***
	 * 사용자의 글수정 요청을 받아 DB에 처리하는 매핑
	 * @param model
	 * @param article
	 * @param request
	 * @param session
	 * @return
	 */
	
	@RequestMapping(value="/editArticle.bim")
	public ModelAndView boardEdit(Model model,ArticleVO article, HttpServletRequest request,HttpSession session){
		logger.info("글 제목 : "+ article.getTitle() + "\t 글내용 : " + article.getContents() );
		ArticleVO sessionBoard = (ArticleVO) session.getAttribute("articleInfo");
		article.setIdx(sessionBoard.getIdx());
		System.out.println("===========>>>"+sessionBoard.getTitle());
		ModelAndView mav = new ModelAndView("redirect:/board/viewList.bim");
		try {
			articleService.editArticle(article);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	
	/***
	 * 사용자의 글삭제 요청을 받아 DB에 처리하는 매핑
	 * @param model
	 * @param article
	 * @param request
	 * @param session
	 * @return
	 */
	
	@RequestMapping(value="/deleteArticle.bim")
	public ModelAndView boardDelete(Model model,ArticleVO article, HttpServletRequest request,HttpSession session){
		ModelAndView mav = new ModelAndView("redirect:/board/viewList.bim");
		ArticleVO sessionBoard = (ArticleVO) session.getAttribute("articleInfo");
		MemberVO sessionMember = (MemberVO) session.getAttribute("loginInfo");
		article.setIdx(sessionBoard.getIdx());
		System.out.println("세션 board"+sessionBoard.getWriteId() +"세션아이디" +sessionMember.getId());
		if (!sessionBoard.getWriteId().equals(sessionMember.getId())) {
			System.out.println(sessionBoard.getWriteId().equals(sessionMember.getId()));
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


