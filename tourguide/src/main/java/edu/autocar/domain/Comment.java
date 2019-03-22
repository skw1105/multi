package edu.autocar.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

	private int commentNo;
	private int postId;
	private int parentNo;
	private String content;
	private String writer;
	private Date regDate;
	private Date updateDate;

}
