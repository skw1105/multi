package edu.autocar.dao;

import java.util.List;

import edu.autocar.domain.Membership;

public interface MembershipDao extends CruDao<Membership, String> {
	List<Membership> getMembershipList() throws Exception;
}
