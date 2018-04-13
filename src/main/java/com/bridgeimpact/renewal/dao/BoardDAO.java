package com.bridgeimpact.renewal.dao;

import java.util.List;

import com.bridgeimpact.renewal.dto.BoardVO;

public interface BoardDAO {

	public List<BoardVO> selectAllBoard(String delGb) throws Exception;

	public void insertBoard(BoardVO board) throws Exception;

	public int selectBoardCntById(String Id) throws Exception;

	public int deleteBoardById(String id) throws Exception;

	public int unDeleteBoardById(String id) throws Exception;

	public List<BoardVO> selectAllBoard() throws Exception;

	public String selectBoardDelgbById(String id) throws Exception;

	public BoardVO selectBoardById(String id) throws Exception;



}
