package com.bridgeimpact.renewal.dao;

import java.util.List;

import com.bridgeimpact.renewal.dto.BoardVO;

public interface BoardDAO {

	List<BoardVO> selectAllBoard() throws Exception;
	
	BoardVO selectBoardByIndex(int index) throws Exception;

	void insertBoard(BoardVO board) throws Exception;

	void updateBoard(BoardVO board) throws Exception;

	void deleteBoard(BoardVO board) throws Exception;

	void updateHitCntByIndex(int index) throws Exception;



}
