package com.bridgeimpact.renewal.controller;

import java.util.Enumeration;
import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bridgeimpact.renewal.common.PageUtil;
import com.bridgeimpact.renewal.dto.ArticleVO;
import com.bridgeimpact.renewal.dto.BoardVO;
import com.bridgeimpact.renewal.dto.CommentVO;
import com.bridgeimpact.renewal.dto.FileVO;
import com.bridgeimpact.renewal.dto.MemberVO;
import com.bridgeimpact.renewal.service.ArticleService;
import com.bridgeimpact.renewal.service.BoardService;
import com.bridgeimpact.renewal.service.CommentService;
import com.bridgeimpact.renewal.service.FileService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/board")
public class ArticleController {

	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private ArticleService articleService;

	@Autowired
	private BoardService boardService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private FileService fileService;

	/***
	 * 게시판 글 작성,편집 페이지 이동 맵핑
	 * @return
	 */
	@RequestMapping(value = "/writeForm.bim")
	public String writeForm() {
		return "/board/writeForm";
	}

	@RequestMapping(value = "/editForm.bim")
	public String editForm(String id, int num, Model model, HttpServletRequest request, HttpSession session) {
		
		MemberVO loginMember = (MemberVO) session.getAttribute("loginInfo");
		List<FileVO> fileList = null;
		String beforeUserUrl = request.getHeader("referer");
		// 파라미터 & 로그인 상태 체크
		if (id == null || "".equals(loginMember.getId())) {
			return beforeUserUrl;
		}
		ArticleVO articleVO = new ArticleVO();
		BoardVO boardVO = new BoardVO();
		try {
			articleVO = articleService.selectArticleByIndex(num);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 수정 요청글과 로그인 사용자 비교
		if (!articleVO.getWriteId().equals(loginMember.getId())) {
			return beforeUserUrl;
		}
		try {
			boardVO = boardService.getBoardByid(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/***
		 * 게시글의 첨부 파일 가져오기
		 */
		try {
			fileList = fileService.selectAllFileByIndex(num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("article", articleVO);
		model.addAttribute("board", boardVO);
		model.addAttribute("fileList", fileList);
		return "/board/editForm";
	}

	/***
	 * 게시판에서 사용자가 선택한 글을 조회 유효성 판별 후 - 조회수 증가 - 게시글의 댓글 가져오기 - 반환
	 * @param num : 글번호 (idx)
	 * @param id : 게시판 구분자
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/viewArticle.bim", method = RequestMethod.GET)
	public ModelAndView boardView(String id, int num, Model model, HttpSession session) {

		List<CommentVO> commentList = null;
		List<FileVO> fileList = null;
		ArticleVO article = null;
		Boolean articleResult = null;

		/***
		 * 글번호(idx)의 글 조회하여 해당 게시판과 글의 유효성 판별
		 */
		try {
			articleResult = articleService.checkValidateArticleByIdx(num);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (!articleResult || "".equals(articleResult) || articleResult == null) {
				logger.info("★★★★★>>> 게시판 비공개 혹은 게시글 삭제시 ");
				return new ModelAndView("main/mainForm");
			}

			try {
				article = articleService.selectArticleByIndex(num);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/***
		 * 게시글 조회수 증가
		 */
		try {
			articleService.increaseHitCntByIndex(num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/***
		 * 게시글의 첨부 파일 가져오기
		 */
		try {
			fileList = fileService.selectAllFileByIndex(num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("컨트롤러:" + fileList);
		model.addAttribute("article", article);
		model.addAttribute("fileList", fileList);
		session.setAttribute("articleInfo", article);
		logger.info("article 확인 : " + article.getWriteId());
		return new ModelAndView("/board/viewForm");
	}

	/***
	 * 사용자의 글쓰기 요청을 받아 DB에 처리
	 * @param article
	 * @param request
	 * @param session
	 * @return
	 */

	@RequestMapping(value = "/writeArticle.bim")
	public ModelAndView boardWrite(ArticleVO article, HttpServletRequest request, HttpSession session) {
		String boardId = article.getBoardId();
        String result = "";
		Map<String, String> resultMap = new HashMap<String, String>();
		logger.info("글 제목 : " + article.getTitle() + "\t 글내용 : " + article.getContents());

		/*
		 * Article DB반영 전 로직
		 */
		MemberVO loginMember = (MemberVO) session.getAttribute("loginInfo");
		article.setWriteId(loginMember.getId());
		int insertArticleResult = 0;
		try {
			insertArticleResult = articleService.writeArticle(request,article);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (insertArticleResult == 0) {
				result = "error";
				// TODO 글 작성 실패시 처리구간
			}
		}
		result = "success";
		resultMap.put("result", result);
		ModelAndView mav = new ModelAndView("redirect:/board/viewList.bim?id=" + boardId);
		return mav;
	}


	/***
	 * 사용자의 글수정 요청을 받아 DB에 반영(submit)
	 * @param article
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/editArticle.bim")
	public ModelAndView editArticle(ArticleVO article, HttpServletRequest request, HttpSession session) {
		ArticleVO sessionBoard = (ArticleVO) session.getAttribute("articleInfo");
		article.setIdx(sessionBoard.getIdx());
		article.setWriteId(sessionBoard.getWriteId());
		article.setBoardId(sessionBoard.getBoardId());
		System.out.println("sessionBoard.getBoardId() : " + sessionBoard.getBoardId());

		logger.info("글 제목 : " + article.getTitle() + "\t 글내용 : " + article.getContents());
		logger.info("글 작성 : " + article.getWriteId() + "\t 글내용 : ");

		Map<String, String> resultMap = new HashMap<String, String>();
		System.out.println("===========>>>" + sessionBoard.getTitle());
		try {
			articleService.editArticle(request, article);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result =  "success";
		resultMap.put("result", result);
		ModelAndView mav = new ModelAndView("redirect:/board/viewList.bim?id=" + article.getBoardId());
		return mav;
	}

	/***
	 * 사용자의 글삭제 요청을 받아 DB에 처리
	 * @param model
	 * @param article
	 * @param request
	 * @param session
	 * @return
	 */

	@RequestMapping(value = "/deleteArticle.bim")
	public ModelAndView boardDelete(String id, int num, Model model, ArticleVO article, HttpServletRequest request,
			HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/board/viewList.bim?id=" + id);
		ArticleVO sessionBoard = (ArticleVO) session.getAttribute("articleInfo");
		MemberVO sessionMember = (MemberVO) session.getAttribute("loginInfo");
		article.setIdx(num);
		System.out.println("세션 board : " + sessionBoard.getWriteId() + "세션아이디 : " + sessionMember.getId());

		if (!sessionBoard.getWriteId().equals(sessionMember.getId())) { // 잘못된 접근 (로그인 사용자와 글 작성자가 다른 요청)
			return mav;
		} else {
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
	 * @param id 				 : 게시판 구분
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/search.bim", method = RequestMethod.GET)
	public String searchView(String id, Model model, HttpServletRequest request) {
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
					logger.info(name + " : " + value);
				}
			}
		}

		if (String.valueOf(id) == null) {
			// TODO 유효하지 않는 게시판 접근시 로직
			return "board/articleList";
		}
		/***
		 * 게시판의 페이징 세팅
		 * 사용자의 검색 요청 정보는 pageUtil 에서 처리합니다
		 */

		PageUtil pageUtil = new PageUtil(request, articleService);


		/***
		 * 검색할 게시판의 게시글 세팅
		 */

		List<ArticleVO> articleList = null;

		try {
			articleList = articleService.selectArticleByPage(pageUtil.getParamMap());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("출력될 게시글 수 : " + pageUtil.getDisplayArticleCnt());
		model.addAttribute("articleList", articleList);
		model.addAttribute("pageUtil", pageUtil);

		logger.info(id + "게시판 요청");
		return "board/articleList";
	}

}
