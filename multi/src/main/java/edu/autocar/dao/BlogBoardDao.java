package edu.autocar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.autocar.domain.BlogBoard;

public interface BlogBoardDao extends CrudDao<BlogBoard, Integer> {
	
	List<BlogBoard> findByTitle(@Param("blogHost")String blogHost,
			@Param("start")int start, @Param("end")int end,
			@Param("text")String text) throws Exception;
	
	void increaseReadCnt(Integer boardId) throws Exception;
	
	int delete(@Param("boardId") Integer boardId,
				@Param("userId") String userId) throws Exception;
}
