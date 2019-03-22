package edu.autocar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.autocar.dao.MemberDao;
import edu.autocar.domain.Member;
import edu.autocar.domain.PageInfo;
import edu.autocar.domain.UserLevel;
import edu.autocar.util.SHA256Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao dao;

	@Override
	public PageInfo<Member> getPage(int page) throws Exception {

		int totalCount = dao.count();
		PageInfo<Member> pi = new PageInfo<>(page, totalCount);
		List<Member> list = dao.getPage(pi.getStart(), pi.getEnd());
		pi.setList(list);
		return pi;
	}

	@Override
	public Member getMember(String userId) throws Exception {
		return dao.findById(userId);
	}

	@Transactional
	@Override
	public void create(Member member) throws Exception {
		System.out.println("create 실행");
		String salt = SHA256Util.generateSalt();
		String encPassword = SHA256Util.getEncrypt(member.getPassword(), salt);
		member.setSalt(salt);
		member.setPassword(encPassword);
		member.setUserLevel(UserLevel.ADMIN);
		System.out.println("UserLevel 세팅");

		dao.insert(member);
	}

	@Transactional
	@Override
	public boolean updateByAdmin(Member member) throws Exception {
		if (!checkAdminPassword(member.getPassword()))
			return false;
		return dao.updateByAdmin(member) == 1;
	}

	private boolean checkAdminPassword(String password) throws Exception {
		Member admin = dao.findById("admin");
		String adminPassword = admin.getPassword();
		password = SHA256Util.getEncrypt(password, // 입력받은 비밀번호
				admin.getSalt()); // admin의 salt
		return adminPassword.equals(password);
	}

	@Transactional
	@Override
	public boolean update(Member member) throws Exception {
		if (checkPassword(member.getUserId(), member.getPassword()) == null)
			return false;

		return dao.update(member) == 1;
	}

	@Transactional
	@Override
	public boolean delete(String userId, String password) throws Exception {
		if (!checkAdminPassword(password))
			return false;
		return dao.delete(userId) == 1;
	}

	@Override
	public Member checkPassword(String userId, String password) throws Exception {
		Member user = dao.findById(userId);
		if (user != null) { // 사용자 ID가 존재하는 경우
			password = SHA256Util.getEncrypt(password, user.getSalt());
			if (password.equals(user.getPassword()))
				return user;
		}
		// ID가 없거나 비밀번호가 다른 경우
		return null;
	}

	@Override
	public boolean multiDelete(String[] users, String password) throws Exception {
		if (!checkAdminPassword(password))
			return false;
		for (String userId : users) {
			dao.delete(userId);
		}
		return true;
	}

}
