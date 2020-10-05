package it.enlea.chirper.acceptance;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.enlea.chirper.client.ChirperClientInterface;
import it.enlea.chirper.client.ConsoleClient;
import it.enlea.chirper.client.ConsoleCommandParser;
import it.enlea.chirper.client.InputCommandParser;
import it.enlea.chirper.logic.ConsoleResponseFormatter;
import it.enlea.chirper.logic.RequestDispatcher;
import it.enlea.chirper.logic.ResponseFormatter;
import it.enlea.chirper.logic.ChirperTimeManager;
import it.enlea.chirper.repository.FollowRepository;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.SessionFollowRepository;
import it.enlea.chirper.repository.SessionPostRepository;




class ChirperAcceptanceTest {
	ChirperClientInterface client ;
	LocalDateTime now;

	@BeforeEach
	void setUp()  {
		PostRepository postRepository = new SessionPostRepository();
		FollowRepository followRepository = new SessionFollowRepository();
		InputCommandParser parser = new ConsoleCommandParser();
		ResponseFormatter formatter = new ConsoleResponseFormatter();
		RequestDispatcher invoker = new RequestDispatcher (postRepository,followRepository, parser, formatter );
		client = new ConsoleClient(invoker);
		now = LocalDateTime.now();
	}

	
	@Test
	void postMessageAcceptanceTest() {
		ChirperTimeManager.getInstance().setClock(createClockMinutesBefore(now,5));
		genericCommandTest("Alice -> I love the weather today","");
		ChirperTimeManager.getInstance().setClock(createClockMinutesBefore(now,2));
		genericCommandTest("Bob -> Damn! We lost!","");
		ChirperTimeManager.getInstance().setClock(createClockMinutesBefore(now,1));
		genericCommandTest("Bob -> Good game though.","");
	}


	
	@Test
	void readingAliceAndBobTimelineAfterTheyPostedAcceptanceTest() {
		insertAliceAndBobPosts();
		ChirperTimeManager.getInstance().setClock(createClockMinutesBefore(now, 0));
		genericCommandTest("Alice", "> I love the weather today (5 minutes ago)\n");
		genericCommandTest("Bob", "> Good game though. (1 minute ago)\n"
								+ "> Damn! We lost! (2 minutes ago)\n");
	}
	@Test
	void charlieFollowAndWallAcceptanceTest() {
		insertAliceAndBobPosts();
		ChirperTimeManager.getInstance().setClock(createClockSecondsBefore(now,2));
		client.processCommand("Charlie -> I'm in New York today! Anyone wants to have a coffee?");
		client.processCommand("Charlie follows Alice");
		String expected =  "> Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)\n"
				+ "> Alice - I love the weather today (5 minutes ago)\n";
		ChirperTimeManager.getInstance().setClock(createClockSecondsBefore(now,0));
		genericCommandTest("Charlie wall", expected);
	
	}
	
	@Test
	void followAndWallComplexAcceptanceTest() {
		insertAliceAndBobPosts();
		ChirperTimeManager.getInstance().setClock(createClockSecondsBefore(now,15));
		client.processCommand("Charlie -> I'm in New York today! Anyone wants to have a coffee?");
		client.processCommand("Charlie follows Alice");
		client.processCommand("Charlie follows Bob");
		String expected = "> Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)\n"
				+ "> Bob - Good game though. (1 minute ago)\n"
				+ "> Bob - Damn! We lost! (2 minutes ago)\n"
				+ "> Alice - I love the weather today (5 minutes ago)\n";
		ChirperTimeManager.getInstance().setClock(createClockSecondsBefore(now,0));
		genericCommandTest("Charlie wall", expected);
	}
	
	@Test
	void followAnUnknowUserAndThenWallAcceptanceTest() {
		insertAliceAndBobPosts();
		ChirperTimeManager.getInstance().setClock(createClockSecondsBefore(now,2));
		client.processCommand("Charlie -> I'm in New York today! Anyone wants to have a coffee?");
		client.processCommand("Charlie follows Dexter");
		String expected =  "> Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)\n";
		ChirperTimeManager.getInstance().setClock(createClockSecondsBefore(now,0));
		genericCommandTest("Charlie wall", expected);
	}
	
	@Test
	void readAnUnknowUserAcceptanceTest() {
		insertAliceAndBobPosts();
		genericCommandTest("David", "");
	}
	
	
	private Clock createClockMinutesBefore(LocalDateTime now, int minute) {
		return Clock.fixed(now.minusMinutes(minute).atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
	}
	private Clock createClockSecondsBefore(LocalDateTime now, int seconds) {
		return Clock.fixed(now.minusSeconds(seconds).atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
	}
	
	private void insertAliceAndBobPosts() {
		ChirperTimeManager.getInstance().setClock(createClockMinutesBefore(now,5));
		client.processCommand("Alice -> I love the weather today");
		ChirperTimeManager.getInstance().setClock(createClockMinutesBefore(now,2));
		client.processCommand("Bob -> Damn! We lost!");
		ChirperTimeManager.getInstance().setClock(createClockMinutesBefore(now,1));
		client.processCommand("Bob -> Good game though.");
	}
	
	private void genericCommandTest(String command, String expected) {
		String actual 		= client.processCommand(command); 
		assertEquals(expected, actual);
	}

}
