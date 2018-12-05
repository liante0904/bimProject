package com.bridgeimpact.renewal.controller;

import java.util.List;
import java.util.Locale;

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
	private MemberService memberService;

	@Autowired
	private BoardService boardService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	
	/***
	 * 관리자 페이지로 이동 맵핑
	 * @param model
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/admin.bim")
	public ModelAndView admin(Model model, Object locale) {
		ModelAndView mv = new ModelAndView("admin/admin");
		return mv;
	}
	
	/***
	 * 관리자가 게시판을 생성하기 위해, 게시판 생성 페이지로 이동(맵핑)
	 * @param model
	 * @param id
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/board/addBoard.bim", method= RequestMethod.GET)
	public ModelAndView addBoard(Model model,String id, HttpServletRequest request,HttpSession session){
		ModelAndView mav = new ModelAndView("admin/board/addBoard");
		return mav;
	}

	/***
	 * 관리자 페이지에서 회원 관리 페이지 이동 맵핑
	 * @param locale
	 * @param model
	 * @return
	 */
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

	/***
	 * 관리자 페이지에서 게시판 관리 페이지 이동 맵핑(현재 게시판과, 게시글을 함께 표시)
	 * @param locale
	 * @param model
	 * @return
	 */
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

}
