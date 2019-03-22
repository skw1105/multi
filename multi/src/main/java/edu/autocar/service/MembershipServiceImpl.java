package edu.autocar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.autocar.dao.MembershipDao;
import edu.autocar.domain.Membership;
import edu.autocar.domain.PageInfo;
import edu.autocar.util.SHA256Util;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MembershipServiceImpl implements MembershipService {
	
	// 스프링이 빈의 요구 사항과 매칭되는 애플리케이션 컨텍스트 상에서
	// 다른 빈을 찾아 빈 간의 의존성을 자동으로 만족시킴
	@Autowired
	MembershipDao dao;
	
	@Override
	public PageInfo<Membership> getPage(int page) throws Exception {
		int totalCount = dao.count();
		PageInfo<Membership> pi = new PageInfo<Membership>(page, totalCount);
		List<Membership> list = dao.getPage(pi.getStart(), pi.getEnd());
		pi.setList(list);
		return pi;
	}

	@Override
	public Membership getMembership(String userId) throws Exception {
		return dao.findById(userId);
	}

	@Transactional
	@Override
	public void create(Membership membership) throws Exception {
		// salt 생성 및 비밀번호 암호화
		String salt = SHA256Util.generateSalt();
		String encPassword = SHA256Util.getEncrypt(membership.getPassword(), salt);
		membership.setSalt(salt);
		membership.setPassword(encPassword);
		dao.insert(membership);
	}

	@Transactional
	@Override
	public boolean update(Membership membership) throws Exception {
		if (checkPassword(membership.getUserId(), membership.getPassword()) == null)
			return false;
		
		return dao.update(membership) == 1;
	}

	@Override
	public Membership checkPassword(String userId, String password) throws Exception {
		Membership user = dao.findById(userId);
		if (user != null) { // 사용자 ID가 존재하는 경우
			password = SHA256Util.getEncrypt(password, user.getSalt());
			if (password.equals(user.getPassword()))
				return user;
		}
		// ID가 없거나 비밀번호가 다른 경우
		return null;
	}

}
