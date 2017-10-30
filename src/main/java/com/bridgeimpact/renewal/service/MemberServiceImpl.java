package com.bridgeimpact.renewal.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.bridgeimpact.renewal.dao.MemberDAO;
import com.bridgeimpact.renewal.dto.MemberVO;;
 
@Service
public class MemberServiceImpl implements MemberService {
 
	@Autowired
    private MemberDAO memberDAO;
    
    @Override
    public List<MemberVO> selectAllMember() throws Exception {
 
        return memberDAO.selectAllMember();
    }

	@Override
	public void insertMember(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		memberDAO.insertMember(member);
	}

	@Override
	public void editMember(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		memberDAO.updateMember(member);
	}
	
	@Override
	public void deleteMember(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		memberDAO.deleteMember(member);
	}

	@Override
	public MemberVO loginMember(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.getMember(member);
	}

	@Override
	public int checkMemberId(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.selectMemberById(member);
	}

 
}


