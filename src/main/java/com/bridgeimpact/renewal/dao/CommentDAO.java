package com.bridgeimpact.renewal.dao;

import java.util.List;

import com.bridgeimpact.renewal.dto.ArticleVO;
import com.bridgeimpact.renewal.dto.CommentVO;

public interface CommentDAO {

	public List<CommentVO> selectAllComment() throws Exception;
	
	public List<CommentVO> selectCommentByIndex(int index) throws Exception;

	public void insertComment(CommentVO comment) throws Exception;

	public void updateComment(CommentVO comment) throws Exception;

	public void deleteComment(CommentVO comment) throws Exception;

	public void updateHitCntByIndex(int index) throws Exception;



}
