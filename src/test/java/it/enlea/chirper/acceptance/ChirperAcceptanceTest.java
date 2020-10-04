package it.enlea.chirper.acceptance;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.enlea.chirper.client.ChirperClientInterface;
import it.enlea.chirper.client.ConsoleClient;
import it.enlea.chirper.client.InputCommandParser;
import it.enlea.chirper.logic.commands.CommandInvoker;
import it.enlea.chirper.repository.FollowRepository;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.SessionFollowRepository;
import it.enlea.chirper.repository.SessionPostRepository;




class ChirperAcceptanceTest {
	static ChirperClientInterface client ;

	@BeforeAll
	static void setUp() throws Exception {
		PostRepository postRepository = new SessionPostRepository();
		FollowRepository followRepository = new SessionFollowRepository();
		CommandInvoker invoker = new CommandInvoker (postRepository,followRepository);
		InputCommandParser parser = new InputCommandParser();
		client = new ConsoleClient(invoker,parser);
	}

	
	@Test
	void postMessageAcceptanceTest() {
		genericCommandTest("Alice -> I love the weather today","");
		genericCommandTest("Bob -> Damn! We lost!","");
		genericCommandTest("Bob -> Good game though","");
	}
	
	@Test
	void readAcceptanceTest() {
		fail();
	}
	@Test
	void followAcceptanceTest() {
		fail();
	}
	
	@Test
	void wallAcceptanceTest() {
		fail();
	}
	
	private void genericCommandTest(String command, String expected) {
		String actual 		= client.processCommand(command); 
		assertEquals(expected, actual);
	}

}
