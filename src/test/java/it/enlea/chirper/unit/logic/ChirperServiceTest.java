package it.enlea.chirper.unit.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.enlea.chirper.logic.ChirperServiceImpl;
import it.enlea.chirper.logic.ConsoleOutputFormatter;
import it.enlea.chirper.logic.ChirperServiceInterface;
import it.enlea.chirper.repository.FollowRepository;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.SessionFollowRepository;
import it.enlea.chirper.repository.SessionPostRepository;
import it.enlea.chirper.repository.model.Post;

class ChirperServiceTest {

	
	ChirperServiceInterface service;
	PostRepository postRepository;
	FollowRepository followRepository;
	SortedSet<Post> testList = new TreeSet<Post>();

	@BeforeEach
	void initService() {
		postRepository = new SessionPostRepository();
		followRepository = new SessionFollowRepository();
		service = new ChirperServiceImpl(postRepository, followRepository);
	}

	@Test
	void insertAPostShouldReturnEmptyString() {
		String output = service.postMessage("elsa", "let it goo");
		assertEquals("",output);
	}
	
	@Test
	void insertAnEmpityPostShouldReturnEmptyStringAndShuldNotAddMessage() {
		String userName = "elsa";
		int previousMessageNum = postRepository.getPostListByUserName(userName).size();
		String output = service.postMessage(userName, "");
		assertEquals("",output);
		assertEquals(previousMessageNum,postRepository.getPostListByUserName(userName).size() );
	}
	
	@Test 
	void readUnkowUserMessagesShouldReturnEmptyString(){
		String output = service.read("olaf");
		assertEquals("",output);
	}
	
	@Test 
	void readUserMessageShouldWork() {
		SortedSet<Post> testList = new TreeSet<Post>();
		LocalDateTime now = LocalDateTime.now();
		testList.add(new Post("anna", "I love winter",  now.minus(10,ChronoUnit.MINUTES)));
		testList.add(new Post("anna", "Olaf where are you?", now.minus(5,ChronoUnit.MINUTES)));
		testList.add(new Post("anna", "Bye bye!", now.minus(2,ChronoUnit.MINUTES)));
		
		for(Post p : testList)
			postRepository.insertPost(p);
		
		String expected	= ConsoleOutputFormatter.formatReadPostList(testList);
		String output	= service.read("anna");
		assertEquals(expected, output);
		
	}
	
	@Test
	void followAUserShouldReturnEmpty() {
		String output = service.follows("elsa", "anna");
		assertEquals("",output);
	}
	
	@Test
	void wallOfAUserShouldWork() {
		createTestPostList();
		insertTestPostListInRepository();
		followRepository.insertFollowRelationship("anna", "elsa");
		followRepository.insertFollowRelationship("anna", "olaf");
		String expected = ConsoleOutputFormatter.formatWallPostList(testList);
		
		String output = service.wall("anna");
		assertEquals(expected, output);
	}
	
	@Test
	void wallOfAnUnknowUserShouldReturnEmpty() {
		createTestPostList();
		insertTestPostListInRepository();
		followRepository.insertFollowRelationship("anna", "elsa");
		String output = service.wall("charile");
		assertEquals("", output);
	}
	
	void wallOfAUserWithNoFollowRelationshipsShouldReturnHisPost() {
		createTestPostList();
		insertTestPostListInRepository();
		
		String username = "anna";
		SortedSet<Post> userPost = postRepository.getPostListByUserName(username);
		
		String expected = ConsoleOutputFormatter.formatWallPostList(userPost);
		String output = service.wall(username);
		
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
