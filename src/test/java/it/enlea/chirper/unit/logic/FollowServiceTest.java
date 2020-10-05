package it.enlea.chirper.unit.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.enlea.chirper.logic.service.FollowService;
import it.enlea.chirper.logic.service.SocialNetworkService;
import it.enlea.chirper.logic.service.parameter.FollowParameters;
import it.enlea.chirper.logic.service.parameter.RequestParametersInterface;
import it.enlea.chirper.repository.FollowRepository;
import it.enlea.chirper.repository.SessionFollowRepository;
import it.enlea.chirper.repository.model.Post;

class FollowServiceTest {

	
	SocialNetworkService command;
	FollowRepository followRepository;
	SortedSet<Post> testList = new TreeSet<Post>();

	@BeforeEach
	void initCommand() {
		followRepository = new SessionFollowRepository();
		command = new FollowService(followRepository);
	}

	
	@Test
	void followAUserShouldReturnEmptyAndUpdateTheFollowingUsers() {
		RequestParametersInterface params = new FollowParameters("elsa", "anna") ;
		command.setParameter(params);
		String output = command.execute(); 
		
		Set<String> following = followRepository.getFollowingUserByUserName("elsa");
		assertEquals("",output);
		assertTrue(following.contains("anna"));
	}
}
