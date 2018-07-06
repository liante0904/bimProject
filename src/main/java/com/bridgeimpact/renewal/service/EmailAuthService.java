package com.bridgeimpact.renewal.service;

import javax.servlet.http.HttpServletRequest;

import com.bridgeimpact.renewal.dto.EmailAuthVO;
import com.bridgeimpact.renewal.dto.MemberVO;

public interface EmailAuthService {
	
	public int insertEmailAuth(HttpServletRequest request, MemberVO member) throws Exception;

	public boolean authEmailByTempKey(String key) throws Exception;

	public int sendEmailByEmailAuthVO(EmailAuthVO emailAuthVO, MemberVO inputMember) throws Exception;

	public int deleteEmailAuth(String idx) throws Exception;

}
