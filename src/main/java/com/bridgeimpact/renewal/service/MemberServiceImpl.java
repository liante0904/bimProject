package com.bridgeimpact.renewal.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgeimpact.renewal.controller.MainController;
import com.bridgeimpact.renewal.dao.MemberDAO;
import com.bridgeimpact.renewal.dto.MemberVO;;
 
@Service
public class MemberServiceImpl implements MemberService {
 
    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
    
	
	@Autowired
    private MemberDAO memberDAO;
	
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    
    
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
	public int loginMember(MemberVO UserRequest) throws Exception {
		// TODO Auto-generated method stub
		int loginResult;
		MemberVO dbMember = 
				memberDAO.getMemberById(UserRequest.getId());
		if (dbMember == null) {
			loginResult = 0;
			return loginResult;
		}
		
		logger.info("TypePassword : " + UserRequest.getPassword() + "\t dbPassword : " + dbMember.getPassword());
		System.out.println("passwordCheck : " +passwordEncoder.matches(UserRequest.getPassword(), dbMember.getPassword()));
		Boolean passwordMatchResult = passwordEncoder.matches(UserRequest.getPassword(), dbMember.getPassword());
		logger.info("login Result : " + passwordMatchResult.toString() );
		if (passwordMatchResult) {
			loginResult = 1;
		} else {
			loginResult = 2;
		}
		return loginResult;
	}

	@Override
	public int checkMemberId(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.selectMemberById(member);
	}

	@Override
	public MemberVO getMemberById(String id) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.getMemberById(id);
	}

 
}


