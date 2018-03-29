package com.bridgeimpact.renewal.controller;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import com.bridgeimpact.renewal.dto.BoardVO;
import com.bridgeimpact.renewal.dto.MemberVO;
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
	 * @param model
	 * @return URL
	 * @throws Exception
	 */
    @RequestMapping(value = {"/" ,"/index.bim"} , method = RequestMethod.GET)
    public String indexForm() {
        return "index";
    }
	@RequestMapping(value="main/loginForm.bim")
	public String loginForm() {
		return "main/loginForm";
	}
	
	@RequestMapping(value="/boardList.bim")
	public ModelAndView writeForm(Model model, HttpServletRequest request){
		List<BoardVO> boardList = null;
		try {
			String delGb = "N";
			boardList = boardService.selectAllBoard(delGb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();
		String jsonList = "";
		try {
			jsonList = mapper.writeValueAsString(boardList);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		logger.info(jsonList);
		model.addAttribute("boardList", boardList);
		return new ModelAndView("/board/boardList");
	}

	/***
	 * 
	 * @param model
	 * @param userInputMember : 사용자 로그인 입력값
	 * @param session
	 * @return
	 */
	@RequestMapping(value="main/login.bim")
	public String loginSubmit(Model model,MemberVO userInputMember,HttpSession session){
		int loginResult = 0;
		String url = "";
		
		/***
		 * 사용자의 로그인 정보를 db 회원정보와 비교
		 * @return loginResult 0 = 아이디 없음, 1 = 로그인 성공 , 2 = 패스워드 불일치 (아이디 존재) 
		 * 
		 */
		try {
			loginResult = memberService.loginMember(userInputMember);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if (loginResult == 1) //	 로그인을 성공한 경우
				try {
					{// 로그인 성공
						model.addAttribute("msg", "로그인 성공");
						MemberVO loginInfo = memberService.getMemberById(userInputMember.getId());
						session.setAttribute("loginInfo", loginInfo);
						url = "main/mainForm";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(loginResult == 2){ // 패스워드가 일치하지 않는 경우
					model.addAttribute("msg", "로그인 실패, 패스워드 불일치");
					url = "main/loginForm";
				}else if(loginResult == 0){//아이디가 존재하지 않는 경우
					model.addAttribute("msg", "아이디가 존재하지 않음");
					url = "main/loginForm";
				}
		}
		
		logger.info(url);
		return url;
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
			String delGb = "N";
			boardList = boardService.selectAllBoard(delGb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();
		String jsonList = "";
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
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout.bim",method = RequestMethod.GET,produces = "application/json; charset=utf8")
	@ResponseBody
	public String logout(Model model, HttpSession session){
		session.invalidate();
		model.addAttribute("success", "로그아웃 성공");
		  return "index";
	}
	
}


