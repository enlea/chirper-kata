package it.enlea.chirper.unit.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.enlea.chirper.logic.ConsoleResponseFormatter;
import it.enlea.chirper.logic.service.SocialNetworkService;
import it.enlea.chirper.logic.service.WallCommand;
import it.enlea.chirper.logic.service.parameter.RequestParametersInterface;
import it.enlea.chirper.logic.service.parameter.WallParameters;
import it.enlea.chirper.repository.FollowRepository;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.SessionFollowRepository;
import it.enlea.chirper.repository.SessionPostRepository;
import it.enlea.chirper.repository.model.Post;

class WallServiceTest {

	
	SocialNetworkService command;
	PostRepository postRepository;
	FollowRepository followRepository;
	SortedSet<Post> testList = new TreeSet<Post>();
	ConsoleResponseFormatter formatter;
	@BeforeEach
	void initCommand() {
		postRepository = new SessionPostRepository();
		followRepository = new SessionFollowRepository();
		formatter = new ConsoleResponseFormatter();
		command = new WallCommand(postRepository, followRepository,formatter);
	}
	@Test
	void wallOfAUserShouldWork() {
		createTestPostList();
		insertTestPostListInRepository();
		followRepository.insertFollowRelationship("anna", "elsa");
		followRepository.insertFollowRelationship("anna", "olaf");
		
		String expected = formatter.formatWallPostList(testList);
		RequestParametersInterface params = new WallParameters("anna");
		command.setParameter(params);
		String output = command.execute();
		assertEquals(expected, output);
	}
	
	@Test
	void wallOfAnUnknowUserShouldReturnEmpty() {
		createTestPostList();
		insertTestPostListInRepository();
		followRepository.insertFollowRelationship("anna", "elsa");
		RequestParametersInterface params = new WallParameters("charile");
		command.setParameter(params);
		String output = command.execute();
		
		assertEquals("", output);
	}
	@Test
	void wallOfAUserWithNoFollowRelationshipsShouldReturnHisPost() {
		createTestPostList();
		insertTestPostListInRepository();
		
		String username = "anna";
		SortedSet<Post> userPost = postRepository.getPostListByUserName(username);
		
		String expected = formatter.formatWallPostList(userPost);
		RequestParametersInterface params = new WallParameters(username);
		command.setParameter(params);
		String output = command.execute();
		
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
