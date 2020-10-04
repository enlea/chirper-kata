package it.enlea.chirper.unit.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.enlea.chirper.logic.commands.FollowCommand;
import it.enlea.chirper.logic.commands.SocialNetworkCommand;
import it.enlea.chirper.repository.FollowRepository;
import it.enlea.chirper.repository.SessionFollowRepository;
import it.enlea.chirper.repository.model.Post;

class FollowCommandTest {

	
	SocialNetworkCommand command;
	FollowRepository followRepository;
	SortedSet<Post> testList = new TreeSet<Post>();

	@BeforeEach
	void initCommand() {
		followRepository = new SessionFollowRepository();
		command = new FollowCommand(followRepository);
	}

	@Test
	void followAUserShouldReturnEmptyAndUpdateTheFollowingUsers() {
		List<String> params = Arrays.asList("elsa", "anna");
		String output = command.execute(params); 
		Set<String> following = followRepository.getFollowingUserByUserName("elsa");
		assertEquals("",output);
		assertTrue(following.contains("anna"));
	}
	
	
}
