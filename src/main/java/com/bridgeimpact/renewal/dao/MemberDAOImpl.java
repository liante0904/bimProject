package com.bridgeimpact.renewal.dao;

import java.util.List;

import javax.inject.Inject;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
 
import com.bridgeimpact.renewal.dto.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
 
    @Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.bridgeimpact.renewal.memberSQL";
    
    @Override
    public List<MemberVO> selectAllMember() throws Exception {
        return sqlSession.selectList(Namespace+".selectAllMember");
    }

	@Override
	public void insertMember(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(Namespace+".insertMember", member);
	}

	@Override
	public void updateMember(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace+".updateMember", member);
	}

	@Override
	public void deleteMember(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace+".deleteMember", member);
	}
	@Override
	public MemberVO getMemberById(String id) throws Exception  {
		// TODO Auto-generated method stub
		return (MemberVO)sqlSession.selectOne(Namespace+".getMember", id);
	}

	@Override
	public int selectMemberById(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		String id = member.getId();
		return sqlSession.selectOne(Namespace+".selectMemberById", id);
	}

 
}
