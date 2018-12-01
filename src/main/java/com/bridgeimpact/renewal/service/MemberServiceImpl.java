package com.bridgeimpact.renewal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.EmitUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgeimpact.renewal.dao.EmailAuthDAO;
import com.bridgeimpact.renewal.dao.MemberDAO;
import com.bridgeimpact.renewal.dto.EmailAuthVO;
import com.bridgeimpact.renewal.dto.MemberVO;;
 
@Service
public class MemberServiceImpl implements MemberService {
 
    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
    
	
	@Autowired
    private MemberDAO memberDAO;
	
	@Autowired
	private EmailAuthDAO emailAuthDAO;
	
    @Autowired
    private EmailAuthService emailAuthService;
    
	@Autowired
    BCryptPasswordEncoder passwordEncoder;
    
    
    @Override
    public List<MemberVO> selectAllMember() throws Exception {
 
        return memberDAO.selectAllMember();
    }

	@Override
	public int insertMember(MemberVO inputMember) throws Exception {
		/***
		 * DB반영전 작업 (입력받은 아이디 중복체크, 암호화 로직 처리 후 DB반영  
		 */
		
		/***
		 * 아이디 중복체크
		 */
		int resultCnt = memberDAO.selectMemberById(inputMember);

		  if ( resultCnt == 1 ){
			  return 0;
			  } 

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
		System.out.println("가입한 회원의 idx : "+inputMember.getIdx());
		
		/***
		 * Eamil 인증 데이터 DB반영 로직
		 * 로직 : 토큰키 생성 -> DB반영 -> 이메일 전송W
		 */
		EmailAuthVO emailAuthVO = new EmailAuthVO(inputMember);
		System.out.println("객체 생성 값 : "+ emailAuthVO.getUserId());

		try {
			emailAuthDAO.insertEmailAuth(emailAuthVO);
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		/***
		 * Email 인증 이메일 발송
		 */
		//TODO Email 인증 데이터 DB 반영 실패시
		int emailAuthResult = emailAuthService.sendEmailByEmailAuthVO(emailAuthVO, inputMember);
		if (emailAuthResult == 0) {
			//TODO 이메일 전송 실패시 에러 처리
			return 10;
		}
		
		return emailAuthResult;
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
		int loginResult = 3; 
		String userStatus = null;
		MemberVO dbMember  = new MemberVO();
		dbMember = 	memberDAO.getMemberById(UserRequest.getId());
		if (dbMember != null) {
			userStatus = dbMember.getType();
		}
		
		if (userStatus == null) { // 아이디가 존재하지 않음
			return loginResult = -1;
		}else if ("0".equals(userStatus)) { // 탈퇴회원
			return loginResult = 0;
		}else if ("2".equals(userStatus)) { // 이메일 미인증 회원(로그인 성공)
			return loginResult = 2;
		}
		/***
		 * 아이디 비밀번호 판단 로직(암호화)
		 */
		logger.info("TypePassword : " + UserRequest.getPassword() + "\t dbPassword : " + dbMember.getPassword());
		logger.info("passwordCheck : " +passwordEncoder.matches(UserRequest.getPassword(), dbMember.getPassword()));
		Boolean passwordMatchResult = passwordEncoder.matches(UserRequest.getPassword(), dbMember.getPassword());
		logger.info("login Result : " + passwordMatchResult.toString() );
		if (passwordMatchResult) { // 아이디 패스워드 일치후 로직구간
			if ("9".equals(userStatus)) { // 로그인에 성공한 관리자
				return loginResult = 9;
			}else if ("2".equals(userStatus)) { // 로그인에 성공 후 이메일 인증 여부
					return loginResult = 2;
				}else if("1".equals(userStatus)){ // 로그인 성공(아이디 패스워드 일치, 이메일 인증 성공 유저)
					return loginResult = 1;
				}
		} else {		// 로그인 실패
			return loginResult = 3;
		}
		return loginResult;
	}

	@Override
	public Map<String, String> checkMemberById(String id) throws Exception {
		// TODO SQL param 변경 (object -> String)
		Map<String, String> resultMap = new HashMap<String, String>();
		MemberVO member = new MemberVO();
		member.setId(id);
		int resultCnt = memberDAO.selectMemberById(member);
		  String result = "";
		  String resultMsg = "";

		  if ( resultCnt == 0 ){
			   result = "success";
			   resultMsg = "사용 가능한 아이디입니다.";
			  } else {
			   result = "error";
			   resultMsg = "이미 사용중인 아이디입니다.";
			  }

		  resultMap.put("result", result);
		  resultMap.put("resultMsg", resultMsg);
		return resultMap;
	}

	@Override
	public MemberVO getMemberById(String id) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.getMemberById(id);
	}

	@Override
	public boolean checkDeleteMemberByPassword(MemberVO sessionMember, String password) throws Exception {
		MemberVO dbMember = new MemberVO();
		dbMember = memberDAO.getMemberById(sessionMember.getId());
		return passwordEncoder.matches(password, dbMember.getPassword());
	}

	@Override
	public Map<String, String> checkMemberEmail(String email) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();
		String result = "";
		String resultMsg = "";
		if (!email.contains("@")) {
		    result = "error";
		    resultMsg = "올바르지 않은 이메일 주소 형식입니다.";
		}else {
			int emailCheckResult = memberDAO.getMemberByEmail(email);
			if (emailCheckResult > 0 ) {
				result = "error";
				resultMsg = "이미 가입된 이메일 주소입니다.";
			}else {
				result = "success";
				resultMsg = "사용 가능한 이메일 주소입니다.";
			}
			resultMap.put("result", result);
			resultMap.put("resultMsg", resultMsg);
		}
		return resultMap;
	}

	@Override
	public boolean findAccountId(MemberVO member) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();
		boolean nameCheck, phoneCheck, emailCheck = false;

		MemberVO dbMember = new MemberVO();
		dbMember = memberDAO.selectMemberByEmail(member);
		logger.info("조회된 Member ID : " + dbMember.getId() + " Name : " + dbMember.getName()
				+ "Phone : " + dbMember.getPhone() + "Email : " + dbMember.getEmail() );
		nameCheck = dbMember.getName().equals(member.getName().trim());
		phoneCheck = dbMember.getPhone().equals(member.getPhone().trim());
		emailCheck = dbMember.getEmail().equals(member.getEmail().trim());

		if ( nameCheck && phoneCheck && emailCheck)
			emailAuthService.sendEmailByAskId(dbMember);

		return true;
	}

	@Override
	public Map<String, String> findAccountPassword(MemberVO member) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();
		String result = "";
		String resultMsg = "";
		// TODO 임시 문자열 패스워드 update 후 해당 패스워드를 이메일로 발송 로직
		String tempPassword = memberDAO.updateMemberTempPasswordByMember(member);
		return resultMap;
	}

 
}


