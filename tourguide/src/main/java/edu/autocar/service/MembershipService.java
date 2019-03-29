package edu.autocar.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.autocar.domain.Membership;
import edu.autocar.domain.PageInfo;

public interface MembershipService {
	PageInfo<Membership> getPage(int page) throws Exception;
	
	List<Membership> getMembershipList() throws Exception;

	Membership getMembership(String userId) throws Exception;

	void create(Membership membership) throws Exception;

	boolean update(Membership membership) throws Exception;
	
	public Membership checkPassword(String userId, String password) throws Exception;
}
