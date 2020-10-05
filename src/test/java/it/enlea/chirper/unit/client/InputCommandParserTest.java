package it.enlea.chirper.unit.client;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import it.enlea.chirper.client.ConsoleCommandParser;
import it.enlea.chirper.logic.service.parameter.FollowParameters;
import it.enlea.chirper.logic.service.parameter.PostParameters;
import it.enlea.chirper.logic.service.parameter.ReadParameters;
import it.enlea.chirper.logic.service.parameter.RequestParametersInterface;
import it.enlea.chirper.logic.service.parameter.UnknowRequestParameter;
import it.enlea.chirper.logic.service.parameter.WallParameters;



class InputCommandParserTest {

	private static ConsoleCommandParser parser;
	
	
	@BeforeAll
	static void setUp() {
		parser = new ConsoleCommandParser();
	}
	
	
	@Test
	void parsePostCommandShouldReturnPostParameters() {
		String command = "Alice -> Hello";
		PostParameters expected 	= new PostParameters("Alice","Hello");
		RequestParametersInterface output 	= parser.parseInputCommand(command);
		assertEquals(output.getClass(),expected.getClass());
		assertEquals(expected.getUserName(), output.getUserName());
		assertEquals(expected.getMessage(), ((PostParameters) output).getMessage());
		
	}
	@Test
	void parseWrongPostCommandShouldReturnUnknownRequestParameter() {
		String command = "Alice -> ";
		testWrongCommand(command);
	}
	
	@Test
	void parseReadTestShouldReturnReadParameters() {
		String command = "Bob";
		ReadParameters expected 	= new ReadParameters("Bob");
		RequestParametersInterface output 	= parser.parseInputCommand(command);
		assertEquals(output.getClass(),expected.getClass());
		assertEquals(expected.getUserName(), output.getUserName());
	}
	
	@Test
	void parseWrongReadCommandShouldReturnUnknownRequestParameter() {
		String command = "Bob read ";
		testWrongCommand(command);
	}
	
	@Test
	void parseFollowsShouldReturnFollowParameters() {
		String command = "Bob follows Alice";
		FollowParameters expected 	= new FollowParameters("Bob","Alice");
		RequestParametersInterface output 	= parser.parseInputCommand(command);
		assertEquals(output.getClass(),expected.getClass());
		assertEquals(expected.getUserName(), output.getUserName());
		assertEquals(expected.getFollowing(), ((FollowParameters)output).getFollowing());
	}
	@Test
	void parseWrongFollowsShouldReturnUnknownRequestParameter() {
		String command = "Bob follows ";
		testWrongCommand(command);
	}
	
	@Test
	void parseWallTestShouldReturnWallParameters() {
		String command = "Bob wall";
		WallParameters expected 	= new WallParameters("Bob");
		RequestParametersInterface output 	= parser.parseInputCommand(command);
		assertEquals(output.getClass(),expected.getClass());
		assertEquals(expected.getUserName(), output.getUserName());
	}
	@Test
	void parseWrongWallShouldReturnUnknownRequestParameter() {
		String command = "wall hello";
		testWrongCommand(command);
	}


	private void testWrongCommand(String command) {
		RequestParametersInterface output 	= parser.parseInputCommand(command);
		assertEquals(output.getClass(), UnknowRequestParameter.class);
	}

}
