package com.bridgeimpact.renewal.service;

import java.util.List;

import com.bridgeimpact.renewal.dto.BoardVO;

public interface BoardService {

	public List<BoardVO> selectAllBoard(String delGb) throws Exception;

	public List<BoardVO> selectAllBoard() throws Exception;
	
	public void insertBoard(BoardVO board) throws Exception;

	public int checkBoardId(String id) throws Exception;

	public int closeBoard(String id) throws Exception;

	public int openBoard(String id) throws Exception;

	public int checkBoardStatusById(String id) throws Exception;

	public BoardVO getBoardByid(String id) throws Exception;


}
