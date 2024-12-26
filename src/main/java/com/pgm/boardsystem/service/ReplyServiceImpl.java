package com.pgm.boardsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pgm.boardsystem.domain.ReplyVO;
import com.pgm.boardsystem.mapper.BoardMapper;
import com.pgm.boardsystem.mapper.ReplyMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor // @Autowired 대신 사용
public class ReplyServiceImpl implements ReplyService {

	private ReplyMapper replyMapper;
	private BoardMapper boardMapper;
	
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		// TODO Auto-generated method stub
		boardMapper.replyCountUpdate(vo.getBno(), 1);
		return replyMapper.insert(vo);
	}

	@Override
	public ReplyVO read(int rno) {
		// TODO Auto-generated method stub
		return replyMapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		// TODO Auto-generated method stub
		return replyMapper.update(vo);
	}

	@Transactional
	@Override
	public int remove(int rno) {
		// TODO Auto-generated method stub
		ReplyVO vo=replyMapper.read(rno);
		boardMapper.replyCountUpdate(vo.getBno(), -1);
		return replyMapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(int bno) {
		// TODO Auto-generated method stub
		return replyMapper.getReplyList(bno);
	}

}
