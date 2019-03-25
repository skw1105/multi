package edu.autocar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.autocar.domain.BlogBoard;

public interface BlogDao extends CruDao<BlogBoard, Integer> {
	int count(String blogHost) throws Exception;
	List<BlogBoard> getPage(@Param("blogHost") String blogHost, @Param("start") int start, @Param("end") int end) throws Exception;
}
