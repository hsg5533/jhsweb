package com.pgm.boardsystem.service;

import java.util.HashMap;
import java.util.List;

import com.pgm.boardsystem.domain.BoardVO;
import com.pgm.boardsystem.domain.FileVO;

public interface BoardService {
	public List<BoardVO> getList();
	List<BoardVO> findPage(HashMap<String,Object> hm);
	BoardVO read(int bno);
	void insert(BoardVO vo);
	int boardCount();
	boolean update(BoardVO vo);
	boolean delete(int bno);
	List<BoardVO> findAll(HashMap<String, Object> hm);
	int boardCount(HashMap<String, Object> hm);
	FileVO getFile(int fno);
	

}
