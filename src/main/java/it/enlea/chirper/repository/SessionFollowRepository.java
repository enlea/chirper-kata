package it.enlea.chirper.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SessionFollowRepository implements FollowRepository {
	
	private Map<String, Set<String>> followingMap;
	
	public SessionFollowRepository() {
		followingMap = new HashMap<String, Set<String>>();
	}

	@Override
	public void insertFollowRelationship(String userName, String following) {
		getFollowingUserByUserName(userName).add(following);
	}

	@Override
	public Set<String> getFollowingUserByUserName(String userName) {
		return followingMap.computeIfAbsent(userName, f -> new HashSet<String>());
	}

}
