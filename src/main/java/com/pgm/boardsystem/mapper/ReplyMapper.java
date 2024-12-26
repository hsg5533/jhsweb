package com.pgm.boardsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pgm.boardsystem.domain.ReplyVO;


@Mapper
public interface ReplyMapper {
	public int insert(ReplyVO vo);
	public ReplyVO read(int rno);
	public int update(ReplyVO vo);
	public int delete(int rno);
	public List<ReplyVO> getReplyList(int bno);
}
