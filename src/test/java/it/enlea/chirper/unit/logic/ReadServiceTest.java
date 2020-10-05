package it.enlea.chirper.unit.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.enlea.chirper.logic.ConsoleOutputFormatter;
import it.enlea.chirper.logic.service.ReadService;
import it.enlea.chirper.logic.service.SocialNetworkService;
import it.enlea.chirper.logic.service.parameter.ReadParameters;
import it.enlea.chirper.logic.service.parameter.RequestParametersInterface;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.SessionPostRepository;
import it.enlea.chirper.repository.model.Post;

class ReadServiceTest {
	
	SocialNetworkService command;
	PostRepository postRepository;

	@BeforeEach
	void initCommand() {
		postRepository = new SessionPostRepository();
		command = new ReadService(postRepository);
	}

	@Test 
	void readUnkowUserMessagesShouldReturnEmptyString2(){
		String username = "olaf";
		RequestParametersInterface params = new ReadParameters(username);
		command.setParameter(params);
		String output = command.execute();
		assertEquals("",output);
		assertTrue(postRepository.getPostListByUserName(username).isEmpty());
	}
	
	@Test 
	void readUserMessageShouldWork() {
		SortedSet<Post> testList = new TreeSet<Post>();
		LocalDateTime now = LocalDateTime.now();
		testList.add(new Post("anna", "I love winter",  now.minus(10,ChronoUnit.MINUTES)));
		testList.add(new Post("anna", "Olaf where are you?", now.minus(5,ChronoUnit.MINUTES)));
		testList.add(new Post("anna", "Bye bye!", now.minus(2,ChronoUnit.MINUTES)));
		
		testList.forEach(p -> postRepository.insertPost(p));
		
		String expected	= ConsoleOutputFormatter.formatReadPostList(testList);

		String username = "anna";
		RequestParametersInterface params = new ReadParameters(username);
		command.setParameter(params);
		
		String output = command.execute();
		assertEquals(expected, output);
		
	}
		
}
