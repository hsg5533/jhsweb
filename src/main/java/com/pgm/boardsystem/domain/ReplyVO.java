package com.pgm.boardsystem.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReplyVO {
	private int rno;
	private int bno;
	private String reply;
	private String replyer;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") // replydate를 날짜형식으로 변환한다. 표준시간대를 서울로 설정한다.
	private Date replydate;
}
