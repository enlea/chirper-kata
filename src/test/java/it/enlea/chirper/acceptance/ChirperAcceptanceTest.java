package it.enlea.chirper.acceptance;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.enlea.chirper.client.ChirperClientInterface;
import it.enlea.chirper.client.ConsoleClient;




class ChirperAcceptanceTest {
	static ChirperClientInterface client ;

	@BeforeAll
	static void setUp() throws Exception {
		client = new ConsoleClient();
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
