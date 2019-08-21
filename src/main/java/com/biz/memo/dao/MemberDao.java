package com.biz.memo.dao;

import java.util.List;

import com.biz.memo.model.MemberVO;

public interface MemberDao {

	public List<MemberVO> SelectAll();
	public MemberVO findByUserId(String m_userid);
	public MemberVO login(MemberVO memberVO);
	public int insert(MemberVO memberVO);
	


}
