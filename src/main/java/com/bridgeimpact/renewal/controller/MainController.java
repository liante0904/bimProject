package com.bridgeimpact.renewal.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bridgeimpact.renewal.dto.BoardVO;
import com.bridgeimpact.renewal.dto.MemberVO;
import com.bridgeimpact.renewal.service.ArticleService;
import com.bridgeimpact.renewal.service.BoardService;
import com.bridgeimpact.renewal.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

 
/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {
    
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    
    @Autowired
    private MemberService memberService;
    
	@Autowired
	private BoardService boardService;

    
    /**
     * Simply selects the home view to render by returning its name.
     */
    
	
	/***
	 * 메인 페이지 init & 로그인 페이지 이동 맵핑
	 * @param locale
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Locale locale, Model model,HttpSession session) throws Exception{
/*
		List<BoardVO> boardList = null;
		try {
			boardList = boardService.selectAllBoard();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("boardList", boardList);
		*/
		
    	return "index";
    }
    @RequestMapping(value = "/index.bim", method = RequestMethod.GET)
    public String indexbim(Locale locale, Model model) throws Exception{
        return "index";
    }
	@RequestMapping(value="main/loginForm.bim")
	public ModelAndView loginForm(Model model) {
		ModelAndView mv = new ModelAndView("main/loginForm");
		return mv;
	}
	
	
	/***
	 * 로그인 페이지에서 로그인 요청 
	 * @param model
	 * @param member
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="main/login.bim")
	public String loginSubmit(Model model,MemberVO member, HttpServletRequest request,HttpSession session){
		MemberVO dbMember = null;
		try {
			dbMember = memberService.loginMember(member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (dbMember != null) {// 로그인 성공
			if (member.getId().equals(dbMember.getId()) && member.getPassword().equals(dbMember.getPassword())) {
				model.addAttribute("msg", "로그인 성공");
				session.setAttribute("loginInfo", dbMember);
				return "main/mainForm";
			}else { // 패스워드가 일치하지 않는 경우
				model.addAttribute("msg", "로그인 실패, 패스워드 불일치");
				return "main/loginForm";
			}
			
			
		}else {//아이디가 존재하지 않는 경우
			model.addAttribute("msg", "아이디가 존재하지 않음");
			System.out.println(">>>>>>> 아이디없음");
			return "main/loginForm";
		}
	}
	
	
	/***
	 * 메인 페이지의 게시판 정보를 요청
	 * @param model
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getBoardList.bim",method = RequestMethod.GET,produces = "application/json; charset=utf8")
	@ResponseBody
	public String getBoardList(Model model,String id, HttpServletRequest request,HttpServletResponse response){
		List<BoardVO> boardList = null;
		try {
			boardList = boardService.selectAllBoard();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();
		String jsonList ="";
		try {
			jsonList = mapper.writeValueAsString(boardList);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		logger.info(jsonList);
		model.addAttribute("boardList", boardList);

		  return jsonList;
	}

	/***
	 * 로그아웃 요청
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout.bim",method = RequestMethod.GET,produces = "application/json; charset=utf8")
	@ResponseBody
	public String logout(Model model, HttpServletRequest request,HttpServletResponse response, HttpSession session){
		session.invalidate();
		model.addAttribute("success", "로그아웃 성공");
		  return "index";
	}
	
}


