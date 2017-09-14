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
public class ArticleController {
    
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    
    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private CommentService commentService;
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    
	
	
	@RequestMapping(value="board/boardList.bim")
	public String boardList(Model model, HttpServletRequest request){
	       List<ArticleVO> boardList = null;
		try {
			boardList = articleService.selectAllArticle();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	        model.addAttribute("boardList", boardList);
	 
		return "board/boardList";
	}
	
	@RequestMapping(value="board/writeForm.bim")
	public String writeForm(Model model, HttpServletRequest request){
		return "board/writeForm";
	}
	@RequestMapping(value="board/editForm.bim")
	public String editForm(Model model, HttpServletRequest request,HttpSession session){
		// session.setAttribute(name, value);
		return "board/editForm";
	}
	
	@RequestMapping(value="board/boardView.bim", method= RequestMethod.GET)
	public ModelAndView boardView(Model model, HttpServletRequest request,HttpSession session){
		int boardIndex = Integer.parseInt(request.getParameter("num"));
		ModelAndView mav = new ModelAndView("board/viewForm");
		ArticleVO selectBoardByIndex = null;
		System.out.println(">>>>>>>>>>>>>"+boardIndex);
		try {
			articleService.increseHitCntByIndex(boardIndex);
			selectBoardByIndex = articleService.selectArticleByIndex(boardIndex);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<CommentVO> selectCommentByIndex = null;
		
		try {
			selectCommentByIndex = commentService.selectCommentByIndex(boardIndex);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.addObject("board" , selectBoardByIndex);
		mav.addObject("commentList" , selectCommentByIndex);
		session.setAttribute("boardInfo", selectBoardByIndex);
		
		return mav;
	}
	
	@RequestMapping(value="board/boardWrite.bim")
	public ModelAndView boardWrite(Model model,ArticleVO board, HttpServletRequest request,HttpSession session){
		logger.info("글 제목 : "+ board.getTitle() + "\t 글내용 : " + board.getContents() );
		MemberVO loginMember = (MemberVO)session.getAttribute("loginInfo");
		board.setWriteId(loginMember.getId());
		ModelAndView mav = new ModelAndView("redirect:/board/boardList.bim");
		try {
			articleService.insertArticle(board);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="board/boardEdit.bim")
	public ModelAndView boardEdit(Model model,ArticleVO board, HttpServletRequest request,HttpSession session){
		logger.info("글 제목 : "+ board.getTitle() + "\t 글내용 : " + board.getContents() );
		ArticleVO sessionBoard = (ArticleVO) session.getAttribute("boardInfo");
		board.setIdx(sessionBoard.getIdx());
		System.out.println("===========>>>"+sessionBoard.getTitle());
		ModelAndView mav = new ModelAndView("redirect:/board/boardList.bim");
		try {
			articleService.editArticle(board);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="board/boardDelete.bim")
	public ModelAndView boardDelete(Model model,ArticleVO board, HttpServletRequest request,HttpSession session){
		ModelAndView mav = new ModelAndView("redirect:/board/boardList.bim");
		ArticleVO sessionBoard = (ArticleVO) session.getAttribute("boardInfo");
		MemberVO sessionMember = (MemberVO) session.getAttribute("loginInfo");
		board.setIdx(sessionBoard.getIdx());
		System.out.println("세션 board"+sessionBoard.getWriteId() +"세션아이디" +sessionMember.getId());
		if (!sessionBoard.getWriteId().equals(sessionMember.getId())) {
			System.out.println(sessionBoard.getWriteId().equals(sessionMember.getId()));
			return mav;
		}else {
			System.out.println("삭제시켜줌?=========>>");
			board.setWriteId(sessionMember.getId());
			try {
				articleService.deleteArticle(board);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return mav;
	}
    
}


