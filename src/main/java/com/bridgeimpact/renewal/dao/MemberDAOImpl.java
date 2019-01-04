package com.bridgeimpact.renewal.dao;

import java.util.List;

import javax.inject.Inject;

import com.bridgeimpact.renewal.dto.EmailAuthVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
 
import com.bridgeimpact.renewal.dto.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
 
    @Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.bridgeimpact.renewal.memberSQL";
    
    @Override
    public List<MemberVO> selectAllMember() {
        return sqlSession.selectList(Namespace+".selectAllMember");
    }

	@Override
	public void insertMember(MemberVO member) {
		sqlSession.insert(Namespace+".insertMember", member);
	}

	@Override
	public void updateMember(MemberVO member) {
		sqlSession.update(Namespace+".updateMember", member);
	}

	@Override
	public void deleteMember(MemberVO member) {
		sqlSession.update(Namespace+".deleteMember", member);
	}
	@Override
	public MemberVO getMemberById(String id) {
		return (MemberVO)sqlSession.selectOne(Namespace+".getMember", id);
	}

	@Override
	public int selectMemberById(MemberVO member) {
		String id = member.getId();
		return sqlSession.selectOne(Namespace+".selectMemberById", id);
	}

	@Override
	public int getMemberByEmail(String email) {
		return sqlSession.selectOne(Namespace+".getMemberByEmail", email);
	}

	@Override
	public MemberVO selectMemberByEmail(MemberVO member) {
		return  sqlSession.selectOne(Namespace+".selectMemberByEmail", member.getEmail());
	}

	@Override
	public void updateMemberPasswordByEmailAuth(EmailAuthVO emailAuthVO) {
		 sqlSession.update(Namespace+".updateMemberPasswordByEmailAuth", emailAuthVO);
	}

	@Override
	public String updateMemberTempPasswordByMember(MemberVO member) {
		return null;
	}


}
