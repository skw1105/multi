package edu.autocar.dao;

import edu.autocar.domain.Member;

public interface MemberDao extends CruDao<Member, String> {
	int updateByAdmin(Member member) throws Exception;
	Member getMember(String userId) throws Exception;
	
}
