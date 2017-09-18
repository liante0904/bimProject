package com.bridgeimpact.renewal.service;

import java.util.List;

import com.bridgeimpact.renewal.dto.BoardVO;

public interface BoardService {

	public List<BoardVO> selectAllBoard() throws Exception;

	public void insertBoard(BoardVO board) throws Exception;

}
