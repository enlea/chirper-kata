package it.enlea.chirper.unit.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.sun.org.apache.bcel.internal.Repository;

import it.enlea.chirper.logic.ChirperServiceImpl;
import it.enlea.chirper.logic.ConsoleFormatter;
import it.enlea.chirper.logic.ChirperServiceInterface;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.SessionPostRepository;
import it.enlea.chirper.repository.model.Post;

class ChirperServiceTest {

	
	static ChirperServiceInterface service;
	static PostRepository postRepository;
	
	@BeforeAll
	static void initService() {
		postRepository = new SessionPostRepository();
		service = new ChirperServiceImpl(postRepository);
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
		List<Post> testList = new ArrayList<Post>();
		LocalDateTime now = LocalDateTime.now();
		testList.add(new Post("anna", "I love winter",  now.minus(10,ChronoUnit.MINUTES)));
		testList.add(new Post("anna", "Olaf where are you?", now.minus(5,ChronoUnit.MINUTES)));
		testList.add(new Post("anna", "Bye bye!", now.minus(2,ChronoUnit.MINUTES)));
		
		for(Post p : testList)
			postRepository.insertPost(p);
		
		Collections.reverse(testList);
		
		String expected	= ConsoleFormatter.formatPostList(testList);
		String output	= service.read("anna");
		assertEquals(expected, output);
		
	}

}
