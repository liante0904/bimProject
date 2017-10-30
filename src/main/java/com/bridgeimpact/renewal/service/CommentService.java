package com.bridgeimpact.renewal.service;

import java.util.List;

import com.bridgeimpact.renewal.dto.ArticleVO;
import com.bridgeimpact.renewal.dto.CommentVO;

public interface CommentService {

    public List<CommentVO> selectAllComment() throws Exception;

    public List<CommentVO> selectCommentByIndex(int index) throws Exception;

    public void insertComment(CommentVO comment) throws Exception;

	public int editComment(CommentVO comment)throws Exception;

	public int deleteComment(CommentVO comment) throws Exception;

	public void increseHitCntByIndex(int index) throws Exception;


}
