package com.bridgeimpact.renewal.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bridgeimpact.renewal.service.EmailAuthService;
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

	@Autowired
	private EmailAuthService emailAuthService;
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
	 * @return 로그인 페이지 반환
	 */
	@RequestMapping(value="member/joinSubmit.bim")
	public String joinSubmit(MemberVO inputMember){
		int result = 0;
		String url = "";
		try {
			result = memberService.insertMember(inputMember);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = 0;
			url = "error";
			return url;
		}
		
		if (result == 1) {
			
			url = "redirect:/main/loginForm.bim";
		}
		return url;
	}
	
	/***
	 * 회원 탈퇴 요청 데이터 반영
	 * @param session
	 * @return 
	 */
	@RequestMapping(value="member/deleteSubmit.bim")
	@ResponseBody
	public Map<String, String> deleteSubmit(Model model, HttpSession session, String password){
		Map<String, String> resultMap = new HashMap<String, String>();
		String result, resultMsg = "";
		
		Boolean userPassworkCheckResult = null;
		MemberVO sessionMember = (MemberVO) session.getAttribute("loginInfo");
		try {
			userPassworkCheckResult = memberService.checkDeleteMemberByPassword(sessionMember, password);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (!userPassworkCheckResult) {
			result = "error";
			resultMsg = "패스워드가 일치하지 않습니다.";
			resultMap.put("result", result);
			resultMap.put("resultMsg", resultMsg);
			return resultMap;
		}
		
		try {
			memberService.deleteMember(sessionMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.invalidate();
		model.addAttribute("success", "회원탈퇴 성공");
		result = "success";
		resultMsg = "회원 탈퇴 처리가 되었습니다.";
		resultMap.put("result", result);
		resultMap.put("resultMsg", resultMsg);
		return resultMap;
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
	
	
	/***
	 * 회원가입 페이지에서 이메일 중복체크 ajax 요청
	 * @param model
	 * @param email
	 * @param request
	 * @param response
	 * @return resultMap (json)
	 */
	@RequestMapping(value="member/checkMemberEmailAjax.bim",method = RequestMethod.POST,produces = "application/json; charset=utf8")
	@ResponseBody
	public Map<String, String> checkMemberEmailAjax(Model model,String email, HttpServletRequest request,HttpServletResponse response){
		  Map<String, String> resultMap = new HashMap<String, String>();
		try {
			resultMap = memberService.checkMemberEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		  return resultMap;
	}
	
	/***
	 * 아이디 찾기 (이메일 정보를 이용한) ajax요청
	 * @param member
	 * @return resultMap (json)
	 */
	@RequestMapping(value="member/askAccountId.bim",method = RequestMethod.POST,produces = "application/json; charset=utf8")
	@ResponseBody
	public Map<String, String> askAccountId(MemberVO member){
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			resultMap = memberService.findAccountId(member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultMap;
	}

	/***
	 *
	 * 패스워드 찾기 (이메일 정보를 이용한) ajax요청
	 * @param member
	 * @return resultMap (json)
	 */
	// TODO 미구현
	@RequestMapping(value="member/askAccountPassword.bim",method = RequestMethod.POST,produces = "application/json; charset=utf8")
	@ResponseBody
	public Map<String, String> askAccountPassword(MemberVO member){
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			resultMap = memberService.findAccountPassword(member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultMap;
	}


	@RequestMapping(value="/emailAuth", method=RequestMethod.GET)
	public String emailConfirm(String key, Model model){
		boolean checkEmailAuthKeyResult = false;
		System.out.println("인증 도착했음" + key);
		try {
			checkEmailAuthKeyResult = emailAuthService.authEmailByTokenKey(key);
			model.addAttribute("check", true);
		} catch (Exception e) {
			model.addAttribute("check", false);
		}
		model.addAttribute("result",checkEmailAuthKeyResult);
		return "/test/emailAuth";
	}
}


