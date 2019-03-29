package edu.autocar.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

	private int lv; //level에 맞게 들여쓰기하기, 댓글임을 보여준다.
	private int cnt; //출력 시작 번호와 마지막 번호 설정을 위한 변수
	private int commentNo;
	private int postId;
	private int parentNo;
	private String content;
	private String writer;
	private Date regDate;
	private Date updateDate;

}
