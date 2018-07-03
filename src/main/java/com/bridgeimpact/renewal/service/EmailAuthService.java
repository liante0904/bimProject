package com.bridgeimpact.renewal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bridgeimpact.renewal.dto.ArticleVO;
import com.bridgeimpact.renewal.dto.EmailAuthVO;
import com.bridgeimpact.renewal.dto.FileVO;
import com.bridgeimpact.renewal.dto.MemberVO;

public interface EmailAuthService {
	
	public int insertEmailAuth(HttpServletRequest request, MemberVO member) throws Exception;

	public int authEmailByTempKey(FileVO file) throws Exception;

	public int sendEmailByEmailAuthVO(EmailAuthVO emailAuthVO) throws Exception;

	public int deleteEmailAuth(String idx) throws Exception;

}
