package com.bridgeimpact.renewal.intercepter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bridgeimpact.renewal.dto.MemberVO;


public class AdminCheckIntercepter extends HandlerInterceptorAdapter {


	/*
	1. preHandle - controller 이벤트 호출전
	2. postHandle - controller 호출 후 view 페이지 출력전
	3. afterCompletion - controller + view 페이지 모두 출력 후

	 * */


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		/***
		 * 관리자 페이지 접근시 세션 처리
		 * 1. 로그인 여부
		 * 2. 로그인한 사용자가 관리자 회원인지 memberType = 9 
		 */
		try {
			MemberVO sessionMember = (MemberVO) request.getSession().getAttribute("loginInfo");
			//로그인 세션 체크
			if(sessionMember != null ){
				// 이용자 타입 체크(관리자 여부)
				if ("9".equals(sessionMember.getType())) {
					return true;
				}else {//TODO 일반 회원시 알림처리 추가
					System.out.println("idType is not 9");
					response.sendRedirect(request.getContextPath() + "/");
					return false;
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		//TODO 비로그인 회원 알림처리 추가(현재 로그인 페이지 반환)
		response.sendRedirect(request.getContextPath() + "/main/loginForm.bim");
		return false;
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


