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
       try {
           //loginInfo이라는 세션key를 가진 정보가 널일경우 로그인 페이지로 이동

           if(request.getSession().getAttribute("loginInfo") == null ){
        	   
                   response.sendRedirect("/main/loginForm.bim");
                   return false;
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       //admin 세션key 존재시 main 페이지 이동
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


