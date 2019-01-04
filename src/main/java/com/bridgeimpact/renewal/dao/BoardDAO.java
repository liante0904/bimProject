package com.bridgeimpact.renewal.dao;

import java.util.List;

import com.bridgeimpact.renewal.dto.BoardVO;

public interface BoardDAO {

	List<BoardVO> selectAllBoard(String delGb) throws Exception;

	void insertBoard(BoardVO board) throws Exception;

	int selectBoardCntById(String Id) throws Exception;

	int deleteBoardById(String id) throws Exception;

	int unDeleteBoardById(String id) throws Exception;

	List<BoardVO> selectAllBoard() throws Exception;

	String selectBoardDelgbById(String id) throws Exception;

	BoardVO selectBoardById(String id) throws Exception;



}
