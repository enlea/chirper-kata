package it.enlea.chirper.unit.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.enlea.chirper.repository.FollowRepository;
import it.enlea.chirper.repository.SessionFollowRepository;

class FollowRepositoryTest {

	FollowRepository repository;
	
	@BeforeEach
	void setUp() {
		repository = new SessionFollowRepository();
	}
	
	@Test
	 void followUserShouldAddFriendToTheFollowingSet() {
		 repository.insertFollowRelationship("anna", "elsa");
		 Set<String> following = repository.getFollowingUserByUserName("anna");
		 assertTrue(following.contains("elsa"));
	}
	
	@Test
	void getFollowingUserByExistentUserNameShouldReturnTheFollowingSet() {
		String user = "anna";
		Set<String> expected 	= new HashSet<String>(Arrays.asList("elsa", "olaf","kristoff"));
		expected.forEach(f -> repository.insertFollowRelationship(user, f));
		Set<String> output 		= repository.getFollowingUserByUserName(user);
		assertEquals(expected, output);
	}
	
	@Test
	void getFollowingUserOfAnUnknowUserShouldReturnEmptySet() {
		repository.insertFollowRelationship("Anna", "elsa");
		Set<String> output 		= repository.getFollowingUserByUserName("elsa");
		assertTrue(output.isEmpty());
	}

}
