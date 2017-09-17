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
@RequestMapping(value = "/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private BoardService boardService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/admin.bim")
	public ModelAndView admin(Model model, Object locale) {
		ModelAndView mv = new ModelAndView("admin/admin");
		return mv;
	}



	@RequestMapping(value = "/memberList.bim", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		List<MemberVO> memberList = null;
		try {
			memberList = memberService.selectAllMember();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("memberList", memberList);

		return "admin/memberList";
	}

	@RequestMapping(value = "/boardList.bim", method = RequestMethod.GET)
	public String boardList(Locale locale, Model model) {

		List<BoardVO> boardList = null;
		List<ArticleVO> articleList = null;
		try {
			boardList = boardService.selectAllBoard();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			articleList = articleService.selectAllArticle();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	 

		model.addAttribute("articleList", articleList);
		model.addAttribute("boardList", boardList);
		return "admin/boardList";
	}

	@RequestMapping(value="/articleList.bim")
	public String boardList(Model model, HttpServletRequest request){
	       List<ArticleVO> articleList = null;
		try {
			articleList = articleService.selectAllArticle();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	        model.addAttribute("articleList", articleList);
	 
		return "/board/boardList";
	}
}
