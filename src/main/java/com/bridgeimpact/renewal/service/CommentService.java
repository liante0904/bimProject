package com.bridgeimpact.renewal.service;

import java.util.List;

import com.bridgeimpact.renewal.dto.ArticleVO;
import com.bridgeimpact.renewal.dto.CommentVO;

public interface CommentService {

    List<CommentVO> selectAllComment() throws Exception;

    List<CommentVO> selectCommentByIndex(int index) throws Exception;

    void insertComment(CommentVO comment) throws Exception;

	int editComment(CommentVO comment)throws Exception;

	int deleteComment(CommentVO comment) throws Exception;

	void increseHitCntByIndex(int index) throws Exception;


}
