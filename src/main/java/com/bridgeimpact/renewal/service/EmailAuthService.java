package com.bridgeimpact.renewal.service;

import javax.servlet.http.HttpServletRequest;

import com.bridgeimpact.renewal.dto.EmailAuthVO;
import com.bridgeimpact.renewal.dto.MemberVO;

public interface EmailAuthService {
	
	int insertEmailAuth(HttpServletRequest request, MemberVO member) throws Exception;

	boolean authEmailByTokenKey(String key) throws Exception;

	int sendEmailByEmailAuthVO(EmailAuthVO emailAuthVO, MemberVO inputMember) throws Exception;

	int sendEmailByAskId(MemberVO member) throws Exception;

	int sendEmailByAskPassword(MemberVO member, EmailAuthVO emailAuthVO) throws Exception;

	int deleteEmailAuth(String idx) throws Exception;

}
