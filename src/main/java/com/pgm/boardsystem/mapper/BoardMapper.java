package com.pgm.boardsystem.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pgm.boardsystem.domain.BoardVO;
import com.pgm.boardsystem.domain.FileVO;



@Mapper
public interface BoardMapper {
	//@Select("select * from tbl_board where bno>0")
	public List<BoardVO> getList(); // 게시글 목록
	public List<BoardVO> findPage(HashMap<String,Object> hm);
	public void insert(BoardVO vo); // 글쓰기
	public int insertSelectKey(BoardVO vo);
	public BoardVO read(int bno); // 게시글 읽음
	public int update(BoardVO vo); // 게시글 수정
	public int delete(int bno); // 게시글 삭제
	public int boardCount();
	public int hitCount(int bno); // 조회수 메소드
	public int boardCount(HashMap<String, Object> hm);
	public List<BoardVO> findAll(HashMap<String, Object> hm);
	public void replyCountUpdate(@Param("bno") int bno,@Param("amount") int amount); 
	public List<FileVO> getFileList(int bno); // 해당 게시물에 파일을 받아오는 메소드
	public int fileRegister(FileVO vo);
	public FileVO getFile(int bno); // 하나의 파일을 받아온다.
	
}
