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

import com.bridgeimpact.renewal.dto.MemberVO;
import com.bridgeimpact.renewal.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

 
/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {
    
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    
    @Autowired
    private MemberService memberService;
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Locale locale, Model model) throws Exception{
 
        return "index";
    }
    @RequestMapping(value = "/index.bim", method = RequestMethod.GET)
    public String indexbim(Locale locale, Model model) throws Exception{
 
        return "index";
    }

	@RequestMapping(value="admin/admin.bim")
	public ModelAndView admin(Model model, Object locale) {
		logger.info("Welcome home! The client locale is {}.", locale);
		ModelAndView mv = new ModelAndView("admin/admin");
		return mv;
	}
	@RequestMapping(value="main/loginForm.bim")
	public ModelAndView loginForm(Model model) {
		ModelAndView mv = new ModelAndView("main/loginForm");
		return mv;
	}
	//로그인, 세션처리
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
	
	
 
}


