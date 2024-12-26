package com.pgm.boardsystem.service;

import java.util.HashMap;
import java.util.List;

import com.pgm.boardsystem.domain.MemberVO;

public interface MemberService {
	public List<MemberVO> getList();
	public void join(MemberVO member);
	public int idCheck(String id);
	public MemberVO loginCheck(String id);
	public MemberVO read(String id);
	public void modify(MemberVO member);
	public void delete(String id);
	public List<MemberVO> findAll(HashMap<String, Object> hm);
	public int memberCount(HashMap<String, Object> hm);

}
