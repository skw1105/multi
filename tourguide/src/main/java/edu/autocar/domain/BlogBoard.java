package edu.autocar.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogBoard {
	private int seq;
	private int boardId;
	private String blogHost;
	@NotEmpty(message = "제목은 필수 항목입니다.")
	private String title;
	private int readCnt;
	private String content;
	private Date regDate;
	private Date updateDate;
	private List<Image> list;


//	public BlogBoard(int boardId) {
//		super();
//		this.boardId = boardId;
//		this.blogHost = blogHost;
//		title = "제목" + boardId;
//		content = "내용 " + boardId;
//		readCnt = 0;
//		regDate = new Date();
//		updateDate = new Date();
//	}
}