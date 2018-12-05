package com.bridgeimpact.renewal.intercepter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoginCheckIntercepter extends HandlerInterceptorAdapter {
    
	/*
	1. preHandle - controller 이벤트 호출전
	2. postHandle - controller 호출 후 view 페이지 출력전
	3. afterCompletion - controller + view 페이지 모두 출력 후
	
	 * */
	
	
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       /***
        * 로그인 여부를 체크하는 인터셉터
        * 세션의 loginInfo 객체 판별
        */
       if(request.getSession().getAttribute("loginInfo") == null ){
           response.sendRedirect(request.getContextPath() + "/main/loginForm.bim");
           return false;
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


