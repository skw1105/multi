package edu.autocar.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private int boardId;
	private String title;
	private String writer;
	private String password;
	private String content;
	private int readCnt;
	private Date regDate;
	private Date updateDate;

	// 테스트 용 생성자
	public Board(int boardId) {
		super();
		this.boardId = boardId;
		title = "제목" + boardId;
		writer = "홍길동 " + boardId;
		content = "내용 " + boardId;
		password = "123456";
		readCnt = 0;
		regDate = new Date();
		updateDate = new Date();
	}
}
