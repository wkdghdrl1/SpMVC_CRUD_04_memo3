package com.biz.memo.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.memo.controller.Memocontroller;
import com.biz.memo.dao.MemberDao;
import com.biz.memo.model.MemberVO;

@Service
public class MemberService {
	
	private static final org.slf4j.Logger logger =  LoggerFactory.getLogger(MemberService.class);
	
	@Autowired
	SqlSession sqlSession;
	
	MemberDao mDao;
	
	@Autowired
	public void getMapper() {
		mDao = sqlSession.getMapper(MemberDao.class);
	}

	public int insert(MemberVO memberVO) {
		// TODO Auto-generated method stub
//		MemberDao mDao = sqlSession.getMapper(MemberDao.class);

		List<MemberVO> mList = mDao.SelectAll();

		if (mList.size() > 0)
			memberVO.setM_role("USER");
		else
			memberVO.setM_role("ADMIN");

		int ret = mDao.insert(memberVO);

		return ret;
	}

	public MemberVO login(MemberVO memberVO) {
		// TODO Auto-generated method stub
		String m_userid = memberVO.getM_userid();
		String m_password = memberVO.getM_password();

//		memberVO = mDao.findByUserId(m_userid);
//		if(memberVO.getM_password().equals(m_password)) {
//			//로그인 성공
//		}
		
		//id와 비밀번호을 select문으로 조회해서
		// 회원정보를 가져오기
		// 만약 id와 비밀번혼가 일치하면 정ㅅ상적인 vo를 리턴
		// 그렇지 않으면 null값을 리턴
		memberVO = mDao.login(memberVO);
		
		logger.debug("MemberVO : " + memberVO);
		return memberVO;
	}
}
