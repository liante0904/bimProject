package com.bridgeimpact.renewal.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeimpact.renewal.dao.CommentDAO;
import com.bridgeimpact.renewal.dto.CommentVO;


@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentDAO commentDAO;
    
	
	@Override
	public List<CommentVO> selectAllComment() throws Exception {
		// TODO Auto-generated method stub
		return commentDAO.selectAllComment();
	}
	
	@Override
	public List<CommentVO> selectCommentByIndex(int index) throws Exception {
		// TODO Auto-generated method stub
		return  commentDAO.selectCommentByIndex(index);
	}

	@Override
	public void insertComment(CommentVO comment) throws Exception {
		// TODO Auto-generated method stub
		commentDAO.insertComment(comment);
	}

	@Override
	public int editComment(CommentVO comment) throws Exception {
		// TODO Auto-generated method stub
		return commentDAO.updateComment(comment);
	}

	@Override
	public int deleteComment(CommentVO comment) throws Exception {
		// TODO Auto-generated method stub
		return commentDAO.deleteComment(comment);
	}

	@Override
	public void increseHitCntByIndex(int index) throws Exception {
		// TODO Auto-generated method stub
		commentDAO.updateHitCntByIndex(index);
	}

}
