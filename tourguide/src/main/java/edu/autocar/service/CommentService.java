package edu.autocar.service;

import edu.autocar.domain.Comment;
import edu.autocar.domain.PageInfo;

public interface CommentService{
	boolean insert(Comment comment) throws Exception;
	PageInfo<Comment> getPage(int gallerId, int page) throws Exception;
}
