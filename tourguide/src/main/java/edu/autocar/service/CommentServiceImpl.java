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
		comment.setParentNo(0);
		comment.setWriter("hotteok");
		return dao.insert(comment) == 1;
	}

	@Override
	public PageInfo<Comment> getPage(int galleryId, int page) throws Exception {
		// TODO Auto-generated method stub
		int totalCount = dao.count(galleryId);
		PageInfo<Comment> pi = new PageInfo<>(page, totalCount);
		List<Comment> list = dao.getPage(galleryId, pi.getStart(), pi.getEnd());
//		for(Comment cmt : list)
//			System.out.println(cmt.getCommentNo() + ", " + cmt.getContent());
		pi.setList(list);
		return pi;
	}
}
