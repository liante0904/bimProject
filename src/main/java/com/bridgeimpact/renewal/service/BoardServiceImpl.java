package com.bridgeimpact.renewal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeimpact.renewal.dao.BoardDAO;
import com.bridgeimpact.renewal.dao.CommentDAO;
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

}
