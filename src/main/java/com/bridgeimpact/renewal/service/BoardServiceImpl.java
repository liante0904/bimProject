package com.bridgeimpact.renewal.service;

import java.util.List;


import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeimpact.renewal.dao.BoardDAO;
import com.bridgeimpact.renewal.dto.BoardVO;


@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDAO boardDAO;
    
	
	@Override
	public List<BoardVO> selectAllBoard() throws Exception {
		// TODO Auto-generated method stub
		return boardDAO.selectAllBoard();
	}
	
	@Override
	public BoardVO selectBoardByIndex(int index) throws Exception {
		// TODO Auto-generated method stub
		return  boardDAO.selectBoardByIndex(index);
	}

	@Override
	public void insertBoard(BoardVO board) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.insertBoard(board);
	}

	@Override
	public void editBoard(BoardVO board) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.updateBoard(board);
	}

	@Override
	public void deleteBoard(BoardVO board) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.deleteBoard(board);
	}

	@Override
	public void increseHitCntByIndex(int index) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.updateHitCntByIndex(index);
	}

}
