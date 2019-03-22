package edu.autocar.service;

import edu.autocar.domain.Membership;
import edu.autocar.domain.PageInfo;

public interface MembershipService {
	//PageInfo<Membership> getPage(int page) throws Exception;

	Membership getMembership(String userId) throws Exception;

	void create(Membership membership) throws Exception;

	//boolean update(Membership membership) throws Exception;
	
	public Membership checkPassword(String userId, String password) throws Exception;
}
