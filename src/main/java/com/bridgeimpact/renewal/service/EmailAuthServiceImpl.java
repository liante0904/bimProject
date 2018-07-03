package com.bridgeimpact.renewal.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bridgeimpact.renewal.common.FileioUtil;
import com.bridgeimpact.renewal.common.MailHandler;
import com.bridgeimpact.renewal.dao.EmailAuthDAO;
import com.bridgeimpact.renewal.dao.FileDAO;
import com.bridgeimpact.renewal.dto.ArticleVO;
import com.bridgeimpact.renewal.dto.EmailAuthVO;
import com.bridgeimpact.renewal.dto.FileVO;
import com.bridgeimpact.renewal.dto.MemberVO;

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
	public int authEmailByTempKey(FileVO file) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmailAuth(String idx) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sendEmailByEmailAuthVO(EmailAuthVO emailAuthVO) throws Exception {
        MailHandler sendMail = new MailHandler(mailSender);
        sendMail.setSubject("[이메일 인증]");
        sendMail.setText(new StringBuffer().append("<h1>메일인증</h1>")
                .append("<a href='http://localhost:8080/spring/emailConfirm?key=")
                .append(emailAuthVO.getEmailAuthKey())
                .append("' target='_blenk'>이메일 인증 확인</a>")
                .toString());
        sendMail.setFrom("kimgoja.com", "김고자");
        String email = "liante0905@naver.com";
        String name = "신승훈";
        sendMail.setTo(email);
        sendMail.send();;
		return 1;
	}
	
}
