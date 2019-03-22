package edu.autocar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.autocar.domain.Comment;

public interface CommentDao extends CruDao<Comment, Integer> {
	int count(int galleryId) throws Exception;
	List<Comment> getPage(@Param("postId") int postId, @Param("start") int start, @Param("end") int end) throws Exception;
}
