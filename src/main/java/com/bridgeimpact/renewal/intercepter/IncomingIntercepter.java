package com.bridgeimpact.renewal.intercepter;


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
	/*
	1. preHandle - controller 이벤트 호출전
	2. postHandle - controller 호출 후 view 페이지 출력전
	3. afterCompletion - controller + view 페이지 모두 출력 후
	
	 * */
	
	
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       /***
        * 게시판 정보를 가져오는 인터셉터
        */
	   
	   List<BoardVO> boardList =  (List<BoardVO>) request.getSession().getAttribute("boardList");
	   System.out.println("여기왔나1");


   		try {
   			String delGb = "N";
   			System.out.println("여기는 오냐");
   			boardList = boardDAO.selectAllBoard(delGb);
   		} catch (Exception e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}finally {
   			request.getSession().setAttribute("boardList", boardList);
   			System.out.println("여기왔나2 tostring"+boardList.toString());
			
		}
       
/*       
       Cookie sessionCookie = new Cookie("boardList", boardList.toString());
       sessionCookie.setMaxAge(3600);
       response.addCookie(sessionCookie);
       

	   Cookie[] ck = request.getCookies();
	   if (sessionCookie != null) {
		for (Cookie c: ck) {
			System.out.println("name:"+c.getName());
			System.out.println("value:"+c.getValue());
		}
	}
	   */
	   
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


