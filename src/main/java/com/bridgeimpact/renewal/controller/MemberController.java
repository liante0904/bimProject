package com.bridgeimpact.renewal.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bridgeimpact.renewal.dto.MemberVO;
import com.bridgeimpact.renewal.service.MemberService;

 
/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {
    
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

	/***
	 * 회원가입, 회원수정 페이지 이동 맵핑
	 * @return 반환 페이지
	 */
	@RequestMapping(value="member/joinForm.bim")
	public String joinForm() {
		return "member/joinForm";
	}
	@RequestMapping(value="member/editForm.bim")
	public String editForm() {
		return "member/editForm";
	}

	
	/***
	 * 회원 수정 페이지에서 작성된 요청 반영 요청
	 * @param session : 로그인 중인 사용자 세션
	 * @param inputMember : 사용자의 입력값(수정할 데이터)
	 * @return 반환 페이지
	 */
	@RequestMapping(value="member/editSubmit.bim")
	public ModelAndView editSubmit(HttpSession session,MemberVO inputMember) {
		ModelAndView mav = new ModelAndView("main/mainForm");

		try {
			memberService.editMember(session,inputMember);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// TODO 회원정보 수정 반영 여부
		}
        
		return mav;
	}


	/***
	 * 회원가입 페이지에서 작성된 데이터 DB반영 요청
	 * @param member
	 * @return 로그인 페이지 반환
	 */
	@RequestMapping(value="member/joinSubmit.bim")
	public String joinSubmit(MemberVO inputMember){
		int result = 0;
		try {
			result = memberService.insertMember(inputMember);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}finally {
			if (result == 0) {
				return "error";
			}
		}
		
		return "redirect:/main/loginForm.bim";
	}
	
	/***
	 * 회원 탈퇴 요청 데이터 반영
	 * @param session
	 * @return 
	 */
	@RequestMapping(value="member/deleteSubmit.bim")
	public String deleteSubmit(HttpSession session){
		
		MemberVO sessionMember = (MemberVO) session.getAttribute("loginInfo");
		
			try {
				memberService.deleteMember(sessionMember);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return "index";
	}
	
	
	/***
	 * 회원가입 페이지에서 아이디 중복체크 ajax 요청
	 * @param model
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="member/checkMemberIdAjax.bim",method = RequestMethod.GET,produces = "application/json; charset=utf8")
	@ResponseBody
	public Map<String, String> memberIdCheck(Model model,String id, HttpServletRequest request,HttpServletResponse response){
		  Map<String, String> resultMap = new HashMap<String, String>();
		try {
			resultMap = memberService.checkMemberById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		    response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		  return resultMap;
	}

}


