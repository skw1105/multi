package edu.autocar.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo<T> {
	public int perPage; // 1페이지 당 데이터 건수
	public int perBlock; // 1블럭 당 페이지 수
	
	int totalCount; // 총 데이터 수
	int totalPage; // 총 페이지 수
	int page; // 현재 페이지 번호
	int perCount; // 한 페이지당 데이터 건수
	List<T> list; // 해당 페이지 목록
	
	int start;
	int end;
	
	int startPage;
	int endPage;
	
	int currentBlock;
	int totalBlock;
	int prevBlockPage;
	int nextBlockPage;
	
	final static private int PER_PAGE_COUNT = 10;
	
	public PageInfo(int currentPage, int total) {
		this(currentPage, total, 10, 10);
	}
	
	public PageInfo(int currentPage, int total, int perPage, int perBlock) {
		page = currentPage;
		this.totalCount = total;
		this.perPage = perPage;
		this.perBlock = perBlock;
		
		this.start = (page - 1) * perPage + 1;
		this.end = start + perPage - 1;
		totalPage = (int) Math.ceil((float)total / perPage);
		
		currentBlock = (int) Math.ceil((float)currentPage / perBlock);
		totalBlock = (int) Math.ceil((float)totalPage / perBlock);
		
		startPage = (currentBlock - 1) * perBlock + 1;
		endPage = startPage + perBlock - 1;
		
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		
		prevBlockPage = page - perBlock;
		nextBlockPage = page + perBlock;
	}
	
//	public PageInfo(int page, int totalCount){
//		this.page = page;
//		this.totalCount = totalCount;
//		
//		this.start = (page - 1) * PER_PAGE_COUNT;
//		this.end = start + PER_PAGE_COUNT;
//	}
}
