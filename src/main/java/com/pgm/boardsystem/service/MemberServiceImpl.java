package com.pgm.boardsystem.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgm.boardsystem.domain.MemberVO;
import com.pgm.boardsystem.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public void join(MemberVO member) {
		// TODO Auto-generated method stub
		memberMapper.join(member);

	}

	@Override
	public int idCheck(String id) {
		// TODO Auto-generated method stub
		return memberMapper.idCheck(id);
	}

	@Override
	public MemberVO loginCheck(String id) {
		// TODO Auto-generated method stub
		return memberMapper.loginCheck(id);
	}

	@Override
	public MemberVO read(String id) {
		// TODO Auto-generated method stub
		return memberMapper.read(id);
	}

	@Override
	public void modify(MemberVO member) {
		memberMapper.modify(member);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		memberMapper.delete(id);
	}

	@Override
	public List<MemberVO> getList() {
		// TODO Auto-generated method stub
		return memberMapper.getList();
	}

	@Override
	public List<MemberVO> findAll(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return memberMapper.findAll(hm);
	}

	@Override
	public int memberCount(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return memberMapper.memberCount(hm);
	}



}
