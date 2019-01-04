package com.bridgeimpact.renewal.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.bridgeimpact.renewal.dto.EmailAuthVO;
import com.bridgeimpact.renewal.dto.MemberVO;

public interface MemberService {
    
    List<MemberVO> selectAllMember() throws Exception;

	int insertMember(MemberVO member) throws Exception;

	void editMember(HttpSession session, MemberVO inputMember) throws Exception;

	void deleteMember(MemberVO member) throws Exception;

	int loginMember(MemberVO member) throws Exception;

	Map<String, String> checkMemberById(String id) throws Exception;

	MemberVO getMemberById(String id) throws Exception;

	boolean checkDeleteMemberByPassword(MemberVO sessionMember, String password) throws Exception;

	Map<String, String> checkMemberEmail(String email) throws Exception;

	boolean findAccountId(MemberVO member) throws Exception;

	Map<String, String> findAccountPassword(MemberVO member) throws Exception;

	boolean ChangePasswordByTokenKey(EmailAuthVO emailAuthVO) throws Exception;
}


