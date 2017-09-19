package com.bridgeimpact.renewal.controller;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
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
import com.bridgeimpact.renewal.dto.CommentVO;
import com.bridgeimpact.renewal.dto.MemberVO;
import com.bridgeimpact.renewal.service.CommentService;
import com.bridgeimpact.renewal.service.MemberService;

 
/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/comment")
public class CommentController {
    
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    
    @Autowired
    private CommentService commentService;
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    
    
	@RequestMapping(value="/writeComment.bim")
	@ResponseBody
	public Map<String, String> writeComment(Model model,CommentVO commentVO, HttpServletRequest request,HttpServletResponse response){
		
		
		Map<String, String> resultMap = new HashMap<String, String>();
		 int resultCnt = 0;
		 
		try {
			 commentService.insertComment(commentVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  String result = "";
		  String resultMsg = "";

		  if ( resultCnt == 0 ){
			   result = "success";
			   resultMsg = "댓글 작성이 완료 되었습니다.";
			  } else {
			   result = "failure";
			   resultMsg = "댓글 작성이 실패 하였습니다.";
			  }

		  resultMap.put("result", result);
		  resultMap.put("resultMsg", resultMsg);
		    response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		  return resultMap;
	}
	
	@RequestMapping(value="/editComment.bim")
	@ResponseBody
	public Map<String, String> editComment(Model model,CommentVO commentVO, HttpServletRequest request,HttpServletResponse response){
		
		
		Map<String, String> resultMap = new HashMap<String, String>();
		 int resultCnt = 0;
		 
		try {
			 commentService.editComment(commentVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  String result = "";
		  String resultMsg = "";

		  if ( resultCnt == 0 ){
			   result = "success";
			   resultMsg = "댓글 수정이 완료 되었습니다.";
			  } else {
			   result = "failure";
			   resultMsg = "댓글 수정이 실패 하였습니다.";
			  }

		  resultMap.put("result", result);
		  resultMap.put("resultMsg", resultMsg);
		    response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		  return resultMap;
	}
	
	@RequestMapping(value="/deleteComment.bim")
	@ResponseBody
	public Map<String, String> deleteComment(Model model,CommentVO commentVO, HttpServletRequest request,HttpServletResponse response){
		
		
		Map<String, String> resultMap = new HashMap<String, String>();
		 int resultCnt = 0;
		 
		try {
			 commentService.deleteComment(commentVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  String result = "";
		  String resultMsg = "";

		  if ( resultCnt == 0 ){
			   result = "success";
			   resultMsg = "댓글 삭제가 완료 되었습니다.";
			  } else {
			   result = "failure";
			   resultMsg = "댓글 삭제가 실패 하였습니다.";
			  }

		  resultMap.put("result", result);
		  resultMap.put("resultMsg", resultMsg);
		    response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		  return resultMap;
	}
	
	
	@RequestMapping(value="/commentList.bim")
	public String boardList(Model model, HttpServletRequest request){
	       List<CommentVO> commentList = null;
		try {
			commentList = commentService.selectAllComment();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	        model.addAttribute("commentList", commentList);
	 
		return "comment/commentList";
	}
	
	@RequestMapping(value="/writeForm.bim")
	public String writeForm(Model model, HttpServletRequest request){
		return "comment/writeForm";
	}
	@RequestMapping(value="/editForm.bim")
	public String editForm(Model model, HttpServletRequest request,HttpSession session){
		// session.setAttribute(name, value);
		return "comment/editForm";
	}
	
	@RequestMapping(value="/boardView.bim", method= RequestMethod.GET)
	public ModelAndView boardView(Model model, HttpServletRequest request,HttpSession session){
		ModelAndView mav = new ModelAndView("comment/viewForm");
		/*
		CommentVO selectCommentByIndex = null;
		try {
			commentService.increseHitCntByIndex(Integer.parseInt(request.getParameter("num")));
			selectCommentByIndex = commentService.selectCommentByIndex(Integer.parseInt(request.getParameter("num")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.addObject("comment" , selectCommentByIndex);
		session.setAttribute("commentInfo", selectCommentByIndex);
		*/
		return mav;
	}

	
    
}


