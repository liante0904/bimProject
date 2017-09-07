package com.bridgeimpact.renewal.service;

import java.util.List;

import com.bridgeimpact.renewal.dto.BoardVO;

public interface BoardService {

    public List<BoardVO> selectAllBoard() throws Exception;

    public BoardVO selectBoardByIndex(int index) throws Exception;

    public void insertBoard(BoardVO board) throws Exception;

	public void editBoard(BoardVO board)throws Exception;

	public void deleteBoard(BoardVO board) throws Exception;

	public void increseHitCntByIndex(int index) throws Exception;


}
