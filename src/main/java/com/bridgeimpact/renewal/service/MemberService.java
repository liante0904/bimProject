package com.bridgeimpact.renewal.service;

import java.util.List;

import com.bridgeimpact.renewal.dto.MemberVO;

public interface MemberService {
    
    public List<MemberVO> selectAllMember() throws Exception;

	public void insertMember(MemberVO member) throws Exception;

	public void editMember(MemberVO member)throws Exception;

	public void deleteMember(MemberVO member)throws Exception;

	public MemberVO loginMember(MemberVO member)throws Exception;

	public int checkMemberId(MemberVO member)throws Exception;
}


