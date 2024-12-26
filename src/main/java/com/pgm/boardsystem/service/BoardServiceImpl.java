package com.pgm.boardsystem.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgm.boardsystem.domain.BoardVO;
import com.pgm.boardsystem.domain.FileVO;
import com.pgm.boardsystem.mapper.BoardMapper;


@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardMapper boardMapper;

	@Override
	public List<BoardVO> getList() {
		// TODO Auto-generated method stub
		return boardMapper.getList();
	}

	@Override
	public BoardVO read(int bno) {
		// TODO Auto-generated method stub
		boardMapper.hitCount(bno);
		List<FileVO> fileList = boardMapper.getFileList(bno);
		BoardVO vo = boardMapper.read(bno);
		vo.setFileList(fileList);
		return vo;
	}

	@Override
	public void insert(BoardVO vo) {
		// TODO Auto-generated method stub
		
		// 만약 게시글이 비어있을 경우
		if (boardMapper.getList().isEmpty()) {
			boardMapper.insert(vo);
		} else {
			// Mapper에서 SelectKey로 bno를 반환하기 때문에 BoardVO에서 bno를 사용할 수 있다.
			boardMapper.insertSelectKey(vo);
		}
		// 람다식 표현
//		vo.getFileList().forEach(fileVO -> {
//			fileVO.setBno(vo.getBno());
//			boardMapper.fileRegister(fileVO);
//		}); 
		for (FileVO fileVO : vo.getFileList()) {
			fileVO.setBno(vo.getBno());
			boardMapper.fileRegister(fileVO);
		}
	}

	@Override
	public int boardCount() {
		// TODO Auto-generated method stub
		return boardMapper.boardCount();
	}

	@Override
	public boolean update(BoardVO vo) {
		// TODO Auto-generated method stub
		return boardMapper.update(vo) == 1;
	}

	@Override
	public boolean delete(int bno) {
		// TODO Auto-generated method stub
		return boardMapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> findPage(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return boardMapper.findPage(hm);
	}

	@Override
	public List<BoardVO> findAll(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return boardMapper.findAll(hm);
	}

	@Override
	public int boardCount(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return boardMapper.boardCount(hm);
	}

	@Override
	public FileVO getFile(int fno) {
		// TODO Auto-generated method stub
		return boardMapper.getFile(fno);
	}
}
