package com.bridgeimpact.renewal.dao;

import javax.inject.Inject;

import com.bridgeimpact.renewal.dto.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bridgeimpact.renewal.dto.EmailAuthVO;

@Repository
public class EmailAuthDAOImpl implements EmailAuthDAO {
    @Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.bridgeimpact.renewal.emailAuthSQL";

	@Override
	public EmailAuthVO insertEmailAuth(MemberVO dbMember, EmailAuthVO emailAuth) {
		sqlSession.insert(Namespace+".insertEmailAuth", emailAuth);
		return emailAuth;
	}

	@Override
	public EmailAuthVO selectEmailAuthByKey(String key) {
		return sqlSession.selectOne(Namespace+".selectEmailAuthByKey", key);
	}

	@Override
	public boolean updateEmailAuthByKey(String key) {
		int resultRow = sqlSession.update(Namespace+".updateEmailAuthByKey", key);
        return resultRow == 2;
    }
    
}
