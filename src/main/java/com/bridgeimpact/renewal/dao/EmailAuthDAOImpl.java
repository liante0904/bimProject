package com.bridgeimpact.renewal.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class EmailAuthDAOImpl implements EmailAuthDAO {
    @Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.bridgeimpact.renewal.emailAuthSQL";

	@Override
	public void insertEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(Namespace+".insertEmail");
	}
    
}
