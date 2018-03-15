package com.bridgeimpact.renewal.controller;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bridgeimpact.renewal.common.PageUtil;
import com.bridgeimpact.renewal.dto.ArticleVO;
import com.bridgeimpact.renewal.dto.CommentVO;
import com.bridgeimpact.renewal.dto.MemberVO;
import com.bridgeimpact.renewal.service.ArticleService;
import com.bridgeimpact.renewal.service.CommentService;

 
/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/board")
public class ArticleController {
    
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    
    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private CommentService commentService;
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    
	/***
	 * 게시판 글 작성,편집  페이지 이동 맵핑
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/writeForm.bim")
	public String writeForm(Model model, HttpServletRequest request){
		return "/board/writeForm";
	}
	@RequestMapping(value="/editForm.bim")
	public String editForm(Model model, HttpServletRequest request,HttpSession session){
		// session.setAttribute(name, value);
		return "/board/editForm";
	}
	 
	
	/***
	 * 게시판에서 사용자가 선택한 글을 보여주는 매핑
	 * @param num 글번호 (idx)
	 * @param model
	 * @param session
	 * @return
	 */

	@RequestMapping(value="/viewArticle.bim", method= RequestMethod.GET)
	public ModelAndView boardView(String id,int num,Model model,HttpSession session){
		String URL = "/board/viewForm";
		int articleIndex = num;
		ModelAndView mav = new ModelAndView(URL);
		ArticleVO selectArticleByIndex = null;
		List<CommentVO> selectCommentByIndex = null;
		try {
			articleService.increseHitCntByIndex(articleIndex);
			selectArticleByIndex = articleService.selectArticleByIndex(articleIndex);
			
			// 게시물 접근여부 판별
			if ("Y".equals(selectArticleByIndex.getBoardDelGb()) ||
					"Y".equals(selectArticleByIndex.getDelGb())) { // 게시글 삭제 여부 혹은 게시판 비공개 여부

				URL = "redirect:/board/viewList.bim?id="+id;
				mav.setViewName(URL);
				System.out.println("★★★★★>>> 게시판 비공개 시 ");
				return mav;
				
			}
			
			/*else if ("Y".equals(selectArticleByIndex.getDelGb())) { // 게시글 삭제 여부
				URL = "redirect:/board/viewList.bim?id="+id;
				mav.setViewName(URL);
				System.out.println("★★★★★>>> 게시글 삭제시 ");
				return mav;
				
			}
			*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			selectCommentByIndex = commentService.selectCommentByIndex(articleIndex);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.addObject("article" , selectArticleByIndex);
		mav.addObject("commentList" , selectCommentByIndex);
		session.setAttribute("articleInfo", selectArticleByIndex);

		return mav;
	}
	
	
	/***
	 * 사용자의 글쓰기 요청을 받아 DB에 처리
	 * @param model
	 * @param article
	 * @param request
	 * @param session
	 * @return
	 */
	
	@RequestMapping(value="/writeArticle.bim")
	@ResponseBody
	public ModelAndView boardWrite(Model model,ArticleVO article, HttpServletRequest request,HttpSession session,MultipartHttpServletRequest multipartHttpServletRequest){
		String boardName = article.getBoardName();
		Map<String, String> resultMap = new HashMap<String, String>();
		logger.info("글 제목 : "+ article.getTitle() + "\t 글내용 : " + article.getContents() );
		
		/*
		 *  Article DB반영전 로직
		 */
		MemberVO loginMember = (MemberVO)session.getAttribute("loginInfo");
		article.setWriteId(loginMember.getId());
		
		try {
			articleService.insertArticle(article);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = "success";
		resultMap.put("result", result);

		/*
		 * 파일 업로드 로직구간 (단일)
		 * 
		 * 		
		 * 
		MultipartFile uploadFile = article.getFiles();
		String fileName = uploadFile.getOriginalFilename();
		String path = request.getSession().getServletContext().getRealPath("/");
		
		System.out.println("UtilFile fileUpload uploadPath : " + path);
        System.out.println("UtilFile fileUpload fileName : " + fileName);
        

        
		if (article.getFiles().isEmpty()) {
			try {
				byte[] fileData = article.getFiles().getBytes();
				System.out.println("?????????"+fileData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		   
        Iterator<String> files = multipart.getFileNames();
        while(files.hasNext()){
            String uploadFile_ = files.next();
            String newFileName = "";
            MultipartFile mFile = multipart.getFile(uploadFile_);
            String fileName_ = mFile.getOriginalFilename();
            System.out.println("실제 파일 이름 : " +fileName);
            newFileName = System.currentTimeMillis()+"."
                    +fileName_.substring(fileName_.lastIndexOf(".")+1);
             
            try {
                mFile.transferTo(new File(path+newFileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		 */
		
		// 다중파일 로직구간
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		java.util.Iterator<String> fileNames = multipartRequest.getFileNames();
		while(fileNames.hasNext())
		{
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String path = request.getSession().getServletContext().getRealPath("/");
			logger.info(path + mFile.getOriginalFilename());
			File file = new File(path + mFile.getOriginalFilename());
			
			if (mFile.getSize() != 0) // File Null Check
			{
				if (!file.exists()) // 경로상에 파일이 존재하지 않을 경우
				{
					if (file.getParentFile().mkdirs()) // 경로에 해당하는 디렉토리들을 생성
					{
						try {
							file.createNewFile(); // 이후 파일 생성
							logger.info(file.getAbsolutePath() + file.getName());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				try {
					mFile.transferTo(file); //임시로 저장된 multipartFile을 실제 파일로 전송
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}




		
		ModelAndView mav = new ModelAndView("/board/viewList.bim?id="+boardName);
		return mav;
	}
	
	/***
	 * 사용자의 글수정 요청을 받아 DB에 처리
	 * @param model
	 * @param article
	 * @param request
	 * @param session
	 * @return
	 */
	
	@RequestMapping(value="/editArticleAjax.bim")
	@ResponseBody
	public Map<String, String> boardEdit(Model model,ArticleVO article, HttpServletRequest request,HttpSession session){
		logger.info("글 제목 : "+ article.getTitle() + "\t 글내용 : " + article.getContents() );
		Map<String, String> resultMap = new HashMap<String, String>();
		ArticleVO sessionBoard = (ArticleVO) session.getAttribute("articleInfo");
		article.setIdx(sessionBoard.getIdx());
		System.out.println("===========>>>"+sessionBoard.getTitle());
		try {
			articleService.editArticle(article);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = "success";
		resultMap.put("result", result);
		return resultMap;
	}
	
	/***
	 * 사용자의 글삭제 요청을 받아 DB에 처리
	 * @param model
	 * @param article
	 * @param request
	 * @param session
	 * @return
	 */
	
	@RequestMapping(value="/deleteArticle.bim")
	public ModelAndView boardDelete(String id,int num,Model model,ArticleVO article, HttpServletRequest request,HttpSession session){
		ModelAndView mav = new ModelAndView("redirect:/board/viewList.bim?id="+id);
		ArticleVO sessionBoard = (ArticleVO) session.getAttribute("articleInfo");
		MemberVO sessionMember = (MemberVO) session.getAttribute("loginInfo");
		article.setIdx(num);
		System.out.println("세션 board : "+sessionBoard.getWriteId() +"세션아이디 : " +sessionMember.getId());
		
		if (!sessionBoard.getWriteId().equals(sessionMember.getId())) { // 잘못된 접근 (로그인 사용자와 글 작성자가 다른 요청)
			return mav;
		}else {
			article.setWriteId(sessionMember.getId());
			try {
				articleService.deleteArticle(article);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return mav;
	}
	
	
 
	/***
	 * 사용자의 검색 요청을 처리합니다.
	 * @param id : 게시판 구분
	 * @param searchKeyword	: 검색 키워드
	 * @param searchType	 : 검색 타입
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/search.bim", method= RequestMethod.GET)
	public String searchView(String id,String searchKeyword,String searchType,Model model, HttpServletRequest request,HttpSession session){
		logger.info(request.getRequestURI().toString());
		logger.info(request.getRequestURL().toString());
		/***
		 * 파라미터 체크 구간
		 */
		Enumeration eNames = request.getParameterNames();
		if (eNames.hasMoreElements()) {
			String title = "Parameters";
			Map entries = new TreeMap();
			while (eNames.hasMoreElements()) {
				String name = (String) eNames.nextElement();
				String[] values = request.getParameterValues(name);
				if (values.length > 0) {
					String value = values[0];
					for (int i = 1; i < values.length; i++) {
						value += "," + values[i];
					}
					logger.info(name+" : "+value);
				}
			}
		}

		
		if (String.valueOf(id) == null) {
			//TODO 유효하지 않는 게시판 접근시 로직
			return "board/articleList";
		}
		/***
		 * 게시판의 페이징 세팅 
		 */
		
		// 이용자의 요청 페이지 세팅
		PageUtil pageUtil = new PageUtil(request, articleService);
		
		
		/***
		 * 검색할 게시판의 게시글 세팅
		 */


	    /***
	     * 사용자의 게시판 검색 파라미터를 설정 합니다.
	     * 
	     * @param boardName : 검색 요청 게시판
	     * @param searchType :검색 요청 타입
	     * @param searchKeyword : 검색어 
	     * @return paramMap : HashMap
	     */
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("boardName", id);
		paramMap.put("searchType", searchType);
		paramMap.put("searchKeyword", searchKeyword);
		
		List<ArticleVO> articleList = null;
		
		try {
//			articleList = articleService.selectArticleByKeyword(paramMap);
			articleList = articleService.selectArticleByPage(pageUtil.getParamMap());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		System.out.println("출력될 게시글 수 : " + pageUtil.getDisplayArticleCnt());
		
		model.addAttribute("articleList", articleList);
		model.addAttribute("pageUtil", pageUtil);

		logger.info(id+"게시판 요청");
		return "board/articleList";
	}
    
}


