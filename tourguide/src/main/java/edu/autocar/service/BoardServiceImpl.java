package edu.autocar.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.validator.internal.util.privilegedactions.SetContextClassLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.autocar.dao.BoardDao;
import edu.autocar.domain.Board;
import edu.autocar.domain.PageInfo;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao dao;

	final static private int PER_PAGE_COUNT = 10;
	int maxId = 0;

	@Override
	public PageInfo<Board> getPage(int page) throws Exception {
		int totalCount = dao.count();
		PageInfo<Board> pi = new PageInfo<>(page, totalCount);
		List<Board> list = dao.getPage(pi.getStart(), pi.getEnd());
		pi.setList(list);
		return pi;
	}

	@Override
	@Transactional
	public Board getBoard(int boardId) throws Exception {
		dao.increaseReadCnt(boardId); // 조회수 증가
		Board board = dao.findById(boardId);
		String content = board.getContent();
		//textarea에서 &nbsp와 \n처리하기
		content = content.replace("\r\n", "<br>");
		content = content.replace(" ", "&nbsp");
		board.setContent(content);
		
		return board;
	}

	@Transactional
	@Override
	public void create(Board board) throws Exception {
		System.out.println("create board");
		System.out.println(board.getContent());
		dao.insert(board);
	}

	@Transactional
	@Override
	public boolean update(Board board) throws Exception {
		return dao.update(board) == 1;
	}

	@Transactional
	@Override
	public boolean delete(int boardId, String password) throws Exception {
		return dao.delete(boardId, password) == 1;
	}

	@Transactional
	@Override
	public boolean insert(Board board) throws Exception {
		return dao.insert(board) == 1;
	}

	@Override
	public boolean increaseReadCnt(int boardId) throws Exception {
		// TODO Auto-generated method stub
		
		return false;
	}

}
