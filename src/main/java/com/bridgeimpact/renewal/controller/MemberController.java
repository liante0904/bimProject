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

    /**
     * Simply selects the home view to render by returning its name.
     */
    
    

	/***
	 * 회원가입, 회원수정 페이지 이동 맵핑
	 * @param model
	 * @return
	 */
	@RequestMapping(value="member/joinForm.bim")
	public ModelAndView joinForm(Model model) {
		ModelAndView mv = new ModelAndView("member/joinForm");
		String password ="1234";
		String encryptPassword = passwordEncoder.encode(password);
		logger.info("encryptPassword: " + encryptPassword);
		System.out.println(encryptPassword);
		System.out.println(passwordEncoder.matches(password, encryptPassword));

		return mv;
	}
	@RequestMapping(value="member/editForm.bim")
	public ModelAndView editForm(Model model,HttpSession session) {
		ModelAndView mv = new ModelAndView("member/editForm");
		return mv;
	}

	
	/***
	 * 회원 수정 페이지에서 작성된 요청 반영 요청
	 * @param model
	 * @param session
	 * @param inputMember
	 * @return
	 */
	@RequestMapping(value="member/editSubmit.bim")
	public ModelAndView editSubmit(Model model,HttpSession session,MemberVO inputMember) {
		ModelAndView mv = new ModelAndView("main/mainForm");
		MemberVO sessionMember= (MemberVO) session.getAttribute("loginInfo");
		inputMember.setIdx(sessionMember.getIdx());
		inputMember.setId(sessionMember.getId());
		
		try {
			memberService.editMember(inputMember);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return mv;
	}


	/***
	 * 회원가입 페이지에서 작성된 데이터 반영 요청
	 * @param model
	 * @param member
	 * @return
	 */
	@RequestMapping(value="member/joinSubmit.bim")
	public String joinSubmit(Model model,MemberVO member){
		
/*		
		try {
			request.setCharacterEncoding("UTF-8");
			System.out.println(member.getId());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/		
		String password = member.getPassword();
		String encryptPassword = passwordEncoder.encode(password);
		logger.info("encryptPassword: " + encryptPassword);
		System.out.println(encryptPassword);
		System.out.println(passwordEncoder.matches(password, encryptPassword));
		System.out.println(member.getId());
		member.setPassword(encryptPassword);
		try {
			memberService.insertMember(member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "redirect:/main/loginForm.bim";
	}
	
	/***
	 * 회원 탈퇴 요청 데이터 반영
	 * @param model
	 * @param member
	 * @param session
	 * @return
	 */
	@RequestMapping(value="member/deleteSubmit.bim")
	public String deleteSubmit(Model model,MemberVO member, HttpSession session){
		
		MemberVO sessionMember = (MemberVO) session.getAttribute("loginInfo");
		
			try {
				memberService.deleteMember(sessionMember);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return "main/loginForm";
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
		System.out.println(id);
		MemberVO member = new MemberVO();
		member.setId(id);
		 Map<String, String> resultMap = new HashMap<String, String>();
		 int resultCnt = 0;
		try {
			resultCnt = memberService.checkMemberId(member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  String result = "";
		  String resultMsg = "";

		  if ( resultCnt == 0 ){
			   result = "success";
			   resultMsg = "사용가능한 아이디입니다.";
			  } else {
			   result = "failure";
			   resultMsg = "이미 사용중인 아이디입니다.";
			  }

		  resultMap.put("result", result);
		  resultMap.put("resultMsg", resultMsg);
		    response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		  return resultMap;
	}

}


