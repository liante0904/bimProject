package com.bridgeimpact.renewal.dao;

import java.util.List;

import com.bridgeimpact.renewal.dto.BoardVO;

public interface BoardDAO {

	public List<BoardVO> selectAllBoard(String delGb) throws Exception;



	public void insertBoard(BoardVO board) throws Exception;

	public int selectBoardById(String Id) throws Exception;

	public int deleteBoardById(String id) throws Exception;



	public List<BoardVO> selectAllBoard() throws Exception;



}
