package com.bridgeimpact.renewal.service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bridgeimpact.renewal.common.MailHandler;
import com.bridgeimpact.renewal.dao.EmailAuthDAO;
import com.bridgeimpact.renewal.dto.EmailAuthVO;
import com.bridgeimpact.renewal.dto.MemberVO;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class EmailAuthServiceImpl implements EmailAuthService {

    private static final Logger logger = LoggerFactory.getLogger(EmailAuthServiceImpl.class);
	
	@Autowired
	private EmailAuthDAO emailAuthDAO;

    @Inject
    private JavaMailSender mailSender;
    
	@Override
	public int insertEmailAuth(HttpServletRequest request, MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean authEmailByTokenKey(String key) throws Exception {
		boolean emailAuthResult = emailAuthDAO.updateEmailAuthByKey(key);
		emailAuthDAO.updateEmailAuthByKey(key);
		return emailAuthResult;
	}

	@Override
	public int deleteEmailAuth(String idx) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}/**/

	@Override
	public int sendEmailByEmailAuthVO(EmailAuthVO emailAuthVO, MemberVO inputMember) throws Exception {
		ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentContextPath();
		builder.scheme("https");
		URI newUri = builder.build().toUri();
		System.out.println(newUri);
        MailHandler sendMail = new MailHandler(mailSender);
        sendMail.setSubject("[이메일 인증]");
        sendMail.setText(new StringBuffer().append("<h1>메일인증</h1>")
                .append("<a href='")
				.append(newUri)
				.append("/emailAuth?key=")
                .append(emailAuthVO.getEmailAuthKey())
                .append("' target='_blenk'>이메일 인증 확인</a>").append("<br>").append("<br>").append("<br>")
				.append("기타 문의 사항은 브리지 임팩트 관리자에게 문의해주세요.")
                .toString());
        sendMail.setFrom("bimProject.com/", "브리지 임팩트 관리자");
        String email = inputMember.getEmail();
        String name = inputMember.getName();
        sendMail.setTo(email);
        sendMail.send();;
		return 1;
	}

	@Override
	public int sendEmailByAskId(MemberVO member) throws Exception {
		MailHandler sendMail = new MailHandler(mailSender);
		String email = member.getEmail();
		String name = member.getName();
		logger.info("사용자 아이디 : "+member.getId());
		sendMail.setSubject("[브리지 임팩트 관리자 아이디 찾기 결과]");
		sendMail.setText(new StringBuffer().append("<h1>아이디 찾기 결과</h1>")
				.append("안녕하세요. " + name + "님!").append("<br>")
				.append("요청하신 사용자의 아이디는 ")
				.append("<strong>")
				.append(member.getId())
				.append("</strong>")
				.append(" 입니다.").append("<br>").append("<br>").append("<br>")
				.append("기타 문의 사항은 브리지 임팩트 관리자에게 문의해주세요.")
				.toString());
		sendMail.setFrom("bimProject.com/", "브리지 임팩트 관리자");
		sendMail.setTo(email);
		sendMail.send();;

		return 0;
	}

	@Override
	public int sendEmailByAskPassword(MemberVO member, EmailAuthVO emailAuthVO) throws Exception {
		ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentContextPath();
		MailHandler sendMail = new MailHandler(mailSender);
		builder.scheme("https");
		URI newUri = builder.build().toUri();
		System.out.println(newUri);
		String email = member.getEmail();
		String name = member.getName();
		logger.info("사용자 아이디 :  " + member.getId());
		sendMail.setSubject("[브리지 임팩트 관리자 비밀번호 찾기 요청]");
		sendMail.setText(new StringBuffer().append("<h1>비밀번호 찾기 요청</h1>")
				.append("안녕하세요. " + name + "님!").append("<br>")
				.append("요청하신 회원님의 비밀번호 찾기 요청입니다. ").append("<br>")
				.append("하단의 링크를 클릭하여 새로운 비밀번호 등록을 해주세요. ").append("<br>")
				.append("<a href=' ").append(newUri)
				.append("/updatePasswordForm?key=")
				.append(emailAuthVO.getEmailAuthKey())
				.append("' target='_blenk'>비밀번호 등록하기</a>").append("<br>")
				.append("<br>")
				.append("<br>")
				.append("<br>")
				.append("링크가 정상 작동하지 않을 경우").append("<br>")
				.append("하단의 URL을 복사하여 접속해주세요.").append("<br>")
				.append(newUri)
				.append("/updatePasswordForm?key=")
				.append(emailAuthVO.getEmailAuthKey())
				.append("<br>")
				.append("<br>")
				.append("<br>")
				.append("기타 문의 사항은 브리지 임팩트 관리자에게 문의해주세요.")
				.toString());
		sendMail.setFrom("bimProject.com/", "브리지 임팩트 관리자");
		sendMail.setTo(email);
		sendMail.send();;

		return 0;
	}

}
