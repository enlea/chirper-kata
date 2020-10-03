package it.enlea.chirper.repository;

import java.util.Set;

public interface FollowRepository {
	
	public void insertFollowRelationship(String userName, String following);
	public Set<String> getFollowingUserByUserName(String userName);
}
