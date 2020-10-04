package it.enlea.chirper.unit.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.enlea.chirper.logic.ConsoleOutputFormatter;
import it.enlea.chirper.logic.commands.SocialNetworkCommand;
import it.enlea.chirper.logic.commands.WallCommand;
import it.enlea.chirper.repository.FollowRepository;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.SessionFollowRepository;
import it.enlea.chirper.repository.SessionPostRepository;
import it.enlea.chirper.repository.model.Post;

class WallCommandTest {

	
	SocialNetworkCommand command;
	PostRepository postRepository;
	FollowRepository followRepository;
	SortedSet<Post> testList = new TreeSet<Post>();

	@BeforeEach
	void initCommand() {
		postRepository = new SessionPostRepository();
		followRepository = new SessionFollowRepository();
		command = new WallCommand(postRepository, followRepository);
	}

	
	@Test
	void wallOfAUserShouldWork() {
		createTestPostList();
		insertTestPostListInRepository();
		followRepository.insertFollowRelationship("anna", "elsa");
		followRepository.insertFollowRelationship("anna", "olaf");
		String expected = ConsoleOutputFormatter.formatWallPostList(testList);
		List<String> params = Arrays.asList("anna");
		String output = command.execute(params);
		assertEquals(expected, output);
	}
	
	@Test
	void wallOfAnUnknowUserShouldReturnEmpty() {
		createTestPostList();
		insertTestPostListInRepository();
		followRepository.insertFollowRelationship("anna", "elsa");
		List<String> params = Arrays.asList("charile");
		String output = command.execute(params);
		assertEquals("", output);
	}
	
	void wallOfAUserWithNoFollowRelationshipsShouldReturnHisPost() {
		createTestPostList();
		insertTestPostListInRepository();
		
		String username = "anna";
		SortedSet<Post> userPost = postRepository.getPostListByUserName(username);
		
		String expected = ConsoleOutputFormatter.formatWallPostList(userPost);
		List<String> params = Arrays.asList(username);
		String output = command.execute(params);
		
		assertEquals(expected, output);
		
		
	}

	private void insertTestPostListInRepository() {
		testList.forEach(p -> postRepository.insertPost(p));
	}

	private void createTestPostList() {
		
		LocalDateTime now = LocalDateTime.now();
		testList.add(new Post("anna", "I love winter",  now.minus(10,ChronoUnit.MINUTES)));
		testList.add(new Post("anna", "Olaf where are you?", now.minus(5,ChronoUnit.MINUTES)));
		testList.add(new Post("anna", "Bye bye!", now.minus(2,ChronoUnit.MINUTES)));
		
		testList.add(new Post("elsa", "Let it go, let it gooo", now.minus(7,ChronoUnit.MINUTES)));
		testList.add(new Post("elsa", "Bye bye!", now.minus(3,ChronoUnit.MINUTES)));
		
		testList.add(new Post("olaf", "I love sunny days!!!", now.minus(6,ChronoUnit.MINUTES)));
		testList.add(new Post("olaf", "Play with me!!!", now.minus(4,ChronoUnit.MINUTES)));
		
	}

	
}
