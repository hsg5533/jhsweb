package com.pgm.boardsystem.service;

import java.util.List;

import com.pgm.boardsystem.domain.ReplyVO; 



public interface ReplyService {
	public int register(ReplyVO vo);
	public ReplyVO read(int rno);
	public int modify(ReplyVO vo);
	public int remove(int rno);
	public List<ReplyVO> getList(int bno);
}
