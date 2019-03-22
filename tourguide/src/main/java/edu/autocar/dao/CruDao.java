package edu.autocar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableTransactionManagement 이것에 의해 dao가 검색 됨
public interface CruDao<M, K> {
	int count() throws Exception;

	List<M> getPage(@Param("start") int start, @Param("end") int end) throws Exception;
	//간단한 값 두 개 이상 보낼 때 사용 방법(VO 객체 만들지 않고)
	//자동으로 MAP으로 만들어줌
	
	M findById(K k) throws Exception;

	int insert(M m) throws Exception;

	int update(M m) throws Exception;

	int delete(K k) throws Exception;
}
