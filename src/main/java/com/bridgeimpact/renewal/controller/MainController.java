package com.bridgeimpact.renewal.controller;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.File;
import java.util.ArrayList;
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

import com.bridgeimpact.renewal.dto.ArticleVO;
import com.bridgeimpact.renewal.dto.BoardVO;
import com.bridgeimpact.renewal.dto.FileVO;
import com.bridgeimpact.renewal.dto.MemberVO;
import com.bridgeimpact.renewal.service.ArticleService;
import com.bridgeimpact.renewal.service.BoardService;
import com.bridgeimpact.renewal.service.FileService;
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
	
	@Autowired
	private ArticleService articleService;

	@Autowired
	private FileService fileService;
    
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
    public ModelAndView indexForm(Model model,HttpSession session) {
    	/***
    	 * 최근 게시글 세팅 로직
    	 */
    	List<BoardVO> boardList =  (List<BoardVO>) session.getAttribute("boardList");
    	System.out.println("boardList 확인 : " + boardList);
    	List<ArticleVO> mainArticleList = new ArrayList<ArticleVO>();
    	try {
    		mainArticleList = articleService.selectMainArticleList(boardList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
    	System.out.println("출력 테스트 : " + mainArticleList);
    	model.addAttribute("mainArticleList", mainArticleList);
        return new ModelAndView("index");
    }
	@RequestMapping(value="main/loginForm.bim")
	public String loginForm() {
		return "main/loginForm";
	}
    @RequestMapping(value = "/main.bim" , method = RequestMethod.GET)
    public String main() {
        return "main";
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
		 * @return loginResult  
		 * -1 = 아이디 없음, 0 = 탈퇴된 아이디 , 1 = 로그인 성공 , 2 = 이메일 미인증 회원(패스워드 일치)   
		 *  3 = 패스워드 불일치 (아이디 존재)
		 * 
		 */
		try {
			loginResult = memberService.loginMember(userInputMember);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			logger.info("관리자 구출: " + loginResult);
			if (loginResult == 1 || loginResult == 9) { // 로그인을 성공로직(일반, 관리자회원)
				if (loginResult == 9) { // 관리자 로직
					model.addAttribute("msg", "관리자 로그인 성공");
				}else { // 일반회원 로직
					model.addAttribute("msg", "로그인 성공");
				}
			} 
				try {
					{// 로그인에 성공한 사용자 정보 세션에 저장
						MemberVO loginInfo = memberService.getMemberById(userInputMember.getId());
						session.setAttribute("loginInfo", loginInfo);
						logger.info("관리자 구출: " + loginInfo);

						url = "main/mainForm";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				switch (loginResult) {
				case -1:	// 아이디가 존재 하지 않는 사용자
					model.addAttribute("msg", "아이디가 존재하지 않음");
					url = "main/loginForm";					
					break;
				case 0:	// 탈퇴된 회원
					model.addAttribute("msg", "탈퇴된 회원");
					url = "main/loginForm";
					break;
				case 2:	// 이메일 미인증 회원(로그인 성공)
					model.addAttribute("msg", "이메일 인증이 되지 않은 회원");
					url = "main/loginForm";
					break;
				case 3:	// 패스워드가 일치하지 않는 경우
					model.addAttribute("msg", "로그인 실패, 패스워드 불일치");
					url = "main/loginForm";
					break;
				
				case 9:	// 탈퇴된 회원
					model.addAttribute("msg", "관리자 회원");
					url = "main/mainForm";
					break;


				default:
					model.addAttribute("msg", "올바른 접근이 아닙니다.");
					url = "main/loginForm";	
					break;
				}
				/*
				if(loginResult == 3){ // 패스워드가 일치하지 않는 경우
					model.addAttribute("msg", "로그인 실패, 패스워드 불일치");
					url = "main/loginForm";
				}else if(loginResult == -1){//아이디가 존재하지 않는 경우
					model.addAttribute("msg", "아이디가 존재하지 않음");
					url = "main/loginForm";
				}else if (loginResult == 2) { // 이메일 미인증 회원(패스워드 일치)
					model.addAttribute("msg", "이메일 인증이 되지 않은 회원");
					url = "main/loginForm";
				}else if (loginResult == 0 ) { // 탈퇴된 회원
					model.addAttribute("msg", "탈퇴된 회원");
					url = "main/loginForm";
				}else if (loginResult == 9) { // 관리자 회원
					model.addAttribute("msg", "관리자 회원");
					url = "main/mainForm";
				}
				*/
		}
		
		logger.info(url);
		return url;
	}
	
	/***
	 * 파일 다운로드 요청을 처리합니다.
	 * @param request
	 * @param response
	 * @param num
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/download.bim")
	public ModelAndView download(HttpServletRequest request,HttpServletResponse response, int num)  {
		FileVO fileVO = null;
		try {
			 fileVO = fileService.selectFileByIndex(num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
//		String vanillaPath = "C:\\upload\\";
//		vanillaPath += "4e624c22518285.gif";
		
		// TODO getRealPath("/") 부분 OS 인식가능한 함수로 변경?
		String serverPath = request.getSession().getServletContext().getRealPath("/");
		serverPath += fileVO.getOriginalFileName();
		logger.info("callDownload : " + serverPath);

		System.out.println(serverPath);
		File downloadFile = new File(serverPath);

		if (!downloadFile.canRead()) {
			 new Exception("File can't read(파일을 찾을 수 없습니다)");
		}
		return new ModelAndView("downloadView", "downloadFile", downloadFile);
		// 첫번째 인자 : beanName(id), 두번쨰 인자 :  File Object,
	}

	
	
	/***
	 * 메인 페이지 헤더의 게시판 정보 요청
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


