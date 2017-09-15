package com.bridgeimpact.renewal.controller;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

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

import com.bridgeimpact.renewal.dto.MemberVO;
import com.bridgeimpact.renewal.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

 
/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {
    
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    
    @Autowired
    private MemberService memberService;
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    
    

	
	@RequestMapping(value="member/joinForm.bim")
	public ModelAndView joinForm(Model model) {
		ModelAndView mv = new ModelAndView("member/joinForm");
		return mv;
	}
	@RequestMapping(value="member/editForm.bim")
	public ModelAndView editForm(Model model,HttpSession session) {
		ModelAndView mv = new ModelAndView("member/editForm");
		return mv;
	}
	
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
		System.out.println(member.getId());
		try {
			memberService.insertMember(member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "member/loginForm";
	}
	
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
	
	
	@RequestMapping(value="member/memberIdCheck.bim",method = RequestMethod.GET,produces = "application/json; charset=utf8")
	@ResponseBody
	public Map<String, String> memberIdCheck(Model model,String id, HttpServletRequest request,HttpServletResponse response){
		System.out.println(id);
		MemberVO member = new MemberVO();
		member.setId(id);
		 Map<String, String> resultMap = new HashMap<String, String>();
		 int resultCnt = 0;
		try {
			resultCnt = memberService.idChkMember(member);
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


