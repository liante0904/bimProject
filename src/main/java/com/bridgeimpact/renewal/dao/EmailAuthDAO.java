package com.bridgeimpact.renewal.dao;

import com.bridgeimpact.renewal.dto.EmailAuthVO;
import com.bridgeimpact.renewal.dto.MemberVO;

public interface EmailAuthDAO {

	EmailAuthVO insertEmailAuth(MemberVO dbMember, EmailAuthVO emailAuth) throws Exception;

	EmailAuthVO selectEmailAuthByKey(String key) throws Exception;

	boolean updateEmailAuthByKey(String key) throws Exception;

}
