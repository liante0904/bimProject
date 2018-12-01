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
		 * Email 인증 데이터 DB반영 로직
		 * 로직 : 토큰키 생성 -> DB반영 -> 이메일 전송
		 */
		EmailAuthVO emailAuthVO = new EmailAuthVO(inputMember);
		System.out.println("객체 생성 값 : "+ emailAuthVO.getUserId());

		try {
			emailAuthDAO.insertEmailAuth(inputMember, emailAuthVO);
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

	/***
	 *
	 * @param UserRequest
	 * @return loginCheckState
	 * @throws Exception
	 */
	@Override
	public int loginMember(MemberVO UserRequest) throws Exception {
		int userType = -1;
		MemberVO dbMember  = new MemberVO();
		/* 사용자의 입력받은 아이디를 이용해 DB회원 정보 조회*/
		dbMember = memberDAO.getMemberById(UserRequest.getId());

		/* 조회된 회원의 회원상태(Member.type)를 이용하여 핸들링
		* -1 = 존재하지 않는 회원
		* 0 = 탈퇴된 회원
		* 1 = 일반 회원(이메일 인증 완료)
		* 2 = 이메일 미인증 회원
		* 9 = 관리자 회원
		* */
		/* 아이디, 패스워드 일치와 상관없이 로그인 불가능 회원 */
		if (dbMember == null)  return -1;
		userType = Integer.parseInt(dbMember.getType());
		if (userType != 1 && userType != 9) {
			return userType;
		}
		/***
		 * 아이디 비밀번호 일치여부 판별 로직(암호화)
		 */
		logger.info( "TypePassword : " + UserRequest.getPassword() + "\t dbPassword : " + dbMember.getPassword());
		logger.info( "passwordCheck : " + passwordEncoder.matches( UserRequest.getPassword(), dbMember.getPassword() ) );
		Boolean passwordMatchResult = passwordEncoder.matches( UserRequest.getPassword(), dbMember.getPassword() );
		logger.info( "login Result : " + passwordMatchResult.toString() );
		/* 로그인 시도한 아이디 패스워드 판별 핸들링*/
		if (passwordMatchResult) {		// 아이디 패스워드 일치
			return userType;
		} else if (!passwordMatchResult){		// 아이디, 패스워드 불일치
			userType = 3;
		}
		return userType;
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

	/***
	 * 비밀번호 찾기 로직
	 * 입력한 회원 정보 조회 및 검증 -> Email 토큰키 생성 및 DB반영-> 회원 정보의 이메일로 비밀번호 변경 URL 발송
	 */
	@Override
	public Map<String, String> findAccountPassword(MemberVO member) throws Exception {
		/* 입력한 회원 정보 조회 */
		Map<String, String> resultMap = new HashMap<String, String>();
		boolean idCheck, nameCheck, phoneCheck, emailCheck = false;
		MemberVO dbMember = new MemberVO();
		dbMember = memberDAO.selectMemberByEmail(member);

		/* */

		/***
		 * 조회한 정보와 입력한 정보 체크 후
		 * Email 토큰키 생성 -> DB반영 -> 비밀번호 변경 이메일 전송
		 */


		idCheck = dbMember.getId().equals(member.getId().trim());
		nameCheck = dbMember.getName().equals(member.getName().trim());
		phoneCheck = dbMember.getPhone().equals(member.getPhone().trim());
		emailCheck = dbMember.getEmail().equals(member.getEmail().trim());
		/* 조회한 정보와 입력한 회원 정보 검증 */
		if ( idCheck && nameCheck && phoneCheck && emailCheck){
			/* Email 토큰키 생성 후 DB 반영*/
			EmailAuthVO emailAuthVO = new EmailAuthVO(dbMember);
			System.out.println("객체 생성 값 : "+ emailAuthVO.getUserId());
			emailAuthVO = emailAuthDAO.insertEmailAuth(dbMember, emailAuthVO);
			/* */
			emailAuthService.sendEmailByAskPassword(dbMember, emailAuthVO);

		}

		String result = "";
		String resultMsg = "";
		// TODO 임시 문자열 패스워드 update 후 해당 패스워드를 이메일로 발송 로직
		String tempPassword = memberDAO.updateMemberTempPasswordByMember(member);
		return resultMap;
	}

 
}


