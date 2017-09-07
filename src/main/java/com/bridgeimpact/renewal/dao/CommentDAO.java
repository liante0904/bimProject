package com.bridgeimpact.renewal.dao;

import java.util.List;

import com.bridgeimpact.renewal.dto.BoardVO;
import com.bridgeimpact.renewal.dto.CommentVO;

public interface CommentDAO {

	List<CommentVO> selectAllComment() throws Exception;
	
	List<CommentVO> selectCommentByIndex(int index) throws Exception;

	void insertComment(CommentVO comment) throws Exception;

	void updateComment(CommentVO comment) throws Exception;

	void deleteComment(CommentVO comment) throws Exception;

	void updateHitCntByIndex(int index) throws Exception;



}
