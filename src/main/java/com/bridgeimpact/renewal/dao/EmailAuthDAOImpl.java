package com.bridgeimpact.renewal.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bridgeimpact.renewal.dto.EmailAuthVO;

@Repository
public class EmailAuthDAOImpl implements EmailAuthDAO {
    @Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.bridgeimpact.renewal.emailAuthSQL";

	@Override
	public int insertEmailAuth(EmailAuthVO emailAuth) throws Exception {
		return sqlSession.insert(Namespace+".insertEmailAuth", emailAuth);
	}
    
}
