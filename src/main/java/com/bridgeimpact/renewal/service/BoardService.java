package com.bridgeimpact.renewal.service;

import java.util.List;

import com.bridgeimpact.renewal.dto.BoardVO;

public interface BoardService {

	List<BoardVO> selectAllBoard(String delGb) throws Exception;

	List<BoardVO> selectAllBoard() throws Exception;
	
	void insertBoard(BoardVO board) throws Exception;

	int checkBoardId(String id) throws Exception;

	int closeBoard(String id) throws Exception;

	int openBoard(String id) throws Exception;

	int checkBoardStatusById(String id) throws Exception;

	BoardVO getBoardByid(String id) throws Exception;


}
