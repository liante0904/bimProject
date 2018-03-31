package com.bridgeimpact.renewal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
	public void insertMember(MemberVO inputMember) throws Exception {
		
		/***
		 * DB반영전 패스워드 암호화 로직
		 */
		String password = inputMember.getPassword();
		String encryptPassword = passwordEncoder.encode(password);
		logger.info("encryptPassword: " + encryptPassword);
		System.out.println(passwordEncoder.matches(password, encryptPassword));
		System.out.println(inputMember.getId());
		inputMember.setPassword(encryptPassword);
		memberDAO.insertMember(inputMember);
	}

	@Override
	public void editMember(HttpSession session,MemberVO inputMember) throws Exception {
		
		/***
		 * 회원수정 DB반영전 로직
		 * 입력받은 회원정보에 idx, id값 반영
		 */
		MemberVO sessionMember = (MemberVO) session.getAttribute("loginInfo");
		inputMember.setIdx(sessionMember.getIdx());
		inputMember.setId(sessionMember.getId());
		String password = inputMember.getPassword();
		String encryptPassword = passwordEncoder.encode(password);
		logger.info("encryptPassword: " + encryptPassword);
		System.out.println(passwordEncoder.matches(password, encryptPassword));
		System.out.println(inputMember.getId());
		inputMember.setPassword(encryptPassword);
		memberDAO.updateMember(inputMember);
	}
	
	@Override
	public void deleteMember(MemberVO member) throws Exception {
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
		}else if ("Y".equals(dbMember.getType())) {
			loginResult = 2;
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
	public Map<String, String> checkMemberById(String id) throws Exception {
		// TODO SQL param 변경 (object -> String)
		MemberVO member = new MemberVO();
		member.setId(id);
		int resultCnt = memberDAO.selectMemberById(member);
		  String result = "";
		  String resultMsg = "";

		  if ( resultCnt == 0 ){
			   result = "success";
			   resultMsg = "사용가능한 아이디입니다.";
			  } else {
			   result = "failure";
			   resultMsg = "이미 사용중인 아이디입니다.";
			  }

		  Map<String, String> resultMap = new HashMap<String, String>();
		  resultMap.put("result", result);
		  resultMap.put("resultMsg", resultMsg);
		return resultMap;
	}

	@Override
	public MemberVO getMemberById(String id) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.getMemberById(id);
	}

 
}


