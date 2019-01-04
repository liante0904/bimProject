package com.bridgeimpact.renewal.dao;

import java.util.List;

import com.bridgeimpact.renewal.dto.EmailAuthVO;
import com.bridgeimpact.renewal.dto.MemberVO;
 
public interface MemberDAO {
    
    List<MemberVO> selectAllMember() throws Exception;

	void insertMember(MemberVO member) throws Exception;

	void updateMember(MemberVO member) throws Exception;

	void deleteMember(MemberVO member) throws Exception;

	MemberVO getMemberById(String id) throws Exception;

	int selectMemberById(MemberVO member) throws Exception;

	int getMemberByEmail(String email) throws Exception;

	MemberVO selectMemberByEmail(MemberVO member) throws Exception;

	void updateMemberPasswordByEmailAuth(EmailAuthVO emailAuthVO) throws Exception;

	String updateMemberTempPasswordByMember(MemberVO member);
}
