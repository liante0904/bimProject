package com.bridgeimpact.renewal.intercepter;


import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bridgeimpact.renewal.dao.BoardDAO;
import com.bridgeimpact.renewal.dto.BoardVO;


public class IncomingIntercepter extends HandlerInterceptorAdapter {

	@Autowired
    private BoardDAO boardDAO;
	/***
     * 1. preHandle - controller 이벤트 호출 전 이벤트
     * 2. postHandle - controller 호출 후 view 페이지 출력 전 이벤트
     * 3. afterCompletion - controller + view 페이지가 완전히 출력 된 후 이벤트
     */
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       Enumeration params = request.getParameterNames();
       System.out.println("----------------------------");
       while (params.hasMoreElements()){
           String name = (String)params.nextElement();
           System.out.println(name + " : " +request.getParameter(name));
       }
       System.out.println("----------------------------");


       /***
        * 게시판 정보를 가져오는 인터셉터
        */
	   List<BoardVO> boardList =  (List<BoardVO>) request.getSession().getAttribute("boardList");
	   if (boardList == null){
           //List<ArticleVO> articleList =  (List<ArticleVO>) request.getSession().getAttribute("articleList");
           try {
               String delGb = "N";
               boardList = boardDAO.selectAllBoard(delGb);
           } catch (Exception e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
               return false;
           }finally {
               request.getSession().setAttribute("boardList", boardList);
           }
       }
       return true;
   }

   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       super.postHandle(request, response, handler, modelAndView);
   }

   @Override
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
       super.afterCompletion(request, response, handler, ex);
   }

   @Override
   public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       super.afterConcurrentHandlingStarted(request, response, handler);
   }
}


