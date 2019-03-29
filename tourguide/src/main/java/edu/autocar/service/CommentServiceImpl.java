package edu.autocar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.autocar.dao.CommentDao;
import edu.autocar.domain.Board;
import edu.autocar.domain.Comment;
import edu.autocar.domain.PageInfo;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDao dao;

	@Override
	public boolean insert(Comment comment) throws Exception {
		return dao.insert(comment) == 1;
	}

	@Override
	public PageInfo<Comment> getPage(int boardId, int page) throws Exception {
		int totalCount = dao.count(boardId);
		PageInfo<Comment> pi = new PageInfo<>(page, totalCount);
		List<Comment> list = dao.getPage(boardId, pi.getStart(), pi.getEnd());
		pi.setList(list);
		return pi;
	}
}
