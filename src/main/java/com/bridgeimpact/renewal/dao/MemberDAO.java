package com.bridgeimpact.renewal.dao;

import java.util.List;

import com.bridgeimpact.renewal.dto.MemberVO;
 
public interface MemberDAO {
    
    public List<MemberVO> selectAllMember() throws Exception;

	public void insertMember(MemberVO member) throws Exception;

	public void updateMember(MemberVO member) throws Exception;

	public void deleteMember(MemberVO member) throws Exception;

	public MemberVO getMemberById(String id) throws Exception;

	public int selectMemberById(MemberVO member) throws Exception;

	public int getMemberByEmail(String email) throws Exception;
    
}
