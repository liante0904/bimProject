package com.bridgeimpact.renewal.dao;

import com.bridgeimpact.renewal.dto.EmailAuthVO;
import com.bridgeimpact.renewal.dto.MemberVO;

public interface EmailAuthDAO {

	public EmailAuthVO insertEmailAuth(MemberVO dbMember, EmailAuthVO emailAuth) throws Exception;

	public EmailAuthVO selectEmailAuthByKey(String key) throws Exception;

	public boolean updateEmailAuthByKey(String key) throws Exception;

}
