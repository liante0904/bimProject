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
                .append("' target='_blenk'>이메일 인증 확인</a>")
                .toString());
        sendMail.setFrom("bimProject.com/", "브리지 임팩트 관리자");
        String email = inputMember.getEmail();
        String name = inputMember.getName();
        sendMail.setTo(email);
        sendMail.send();;
		return 1;
	}
	
}
