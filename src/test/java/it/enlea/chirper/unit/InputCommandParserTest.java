package it.enlea.chirper.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.enlea.chirper.client.InputCommandParser;
import it.enlea.chirper.logic.commands.CommandType;



class InputCommandParserTest {

	private static InputCommandParser parser;
	private List<String> expectedParameters = new ArrayList<String>();
	private String command	= new String();
	
	
	@BeforeAll
	static void setUp() {
		parser = new InputCommandParser();
	}
	
	
	@Test
	void parsePostCommandShouldReturnPostAndParameters() {
		//good message
		buildPostMessage("Alice", "Hello");
		parser.parseCommand(command);
		assertEquals(CommandType.POST, parser.getCommandType());
		assertEquals(expectedParameters, parser.getParameters());
		
		buildPostMessage("Alice", "Hello world!");
		parser.parseCommand(command);
		assertEquals(CommandType.POST, parser.getCommandType());
		assertEquals(expectedParameters, parser.getParameters());
		
	}
	@Test
	void parseWrongPostCommandShouldReturnDefaultAndEmptyParameter() {
		//wrong message
		buildCommand("alice"," -> ","");
		parser.parseCommand(command);
		assertEquals(CommandType.DEFAULT, parser.getCommandType());
		assertEquals(new ArrayList<String>(), parser.getParameters());
	}
	
	@Test
	void parseReadTestShouldReturnReadAndParameters() {
		buildCommand("Bob","","");
		parser.parseCommand(command);
		assertEquals(CommandType.READ, parser.getCommandType());
		assertEquals(expectedParameters, parser.getParameters());
	}
	
	@Test
	void parseWrongReadCommandShouldReturnDefaultAndEmptyParameter() {
		//wrong message
		buildCommand("alice"," read", "");
		parser.parseCommand(command);
		assertEquals(CommandType.DEFAULT, parser.getCommandType());
		assertEquals(new ArrayList<String>(), parser.getParameters());
	}
	
	@Test
	void parseFollowsShouldReturnFollowAndParameters() {
		buildCommand("Bob"," follows ","Alice");
		parser.parseCommand(command);
		assertEquals(CommandType.FOLLOW, parser.getCommandType());
		assertEquals(expectedParameters, parser.getParameters());
	}
	@Test
	void parseWrongFollowsShouldReturnDefaultAndEmptyParameter() {
		//wrong message
		buildCommand("Bob"," follows ","");
		parser.parseCommand(command);
		assertEquals(CommandType.DEFAULT, parser.getCommandType());
		assertEquals(new ArrayList<String>(), parser.getParameters());
	}
	
	@Test
	void parseWallTestShouldReturnWallAndParameters() {
		buildCommand("Bob"," wall","");
		parser.parseCommand(command);
		assertEquals(CommandType.WALL, parser.getCommandType());
		assertEquals(expectedParameters, parser.getParameters());
	}
	@Test
	void parseWrongWallShouldReturnDefaultAndEmptyParameter() {
		//wrong message
		buildCommand("wall"," hello","");
		parser.parseCommand(command);
		assertEquals(CommandType.DEFAULT, parser.getCommandType());
		assertEquals(new ArrayList<String>(), parser.getParameters());
	}
	
	//utility methods
	private void buildPostMessage(String user, String message) {
		buildCommand(user, " -> ", message);
	}
	
	private void buildCommand(String param1, String operation, String param2) {
		expectedParameters.clear();
		command = new String();
		if(!param1.isEmpty()) {
			command= param1;
			expectedParameters.add(param1);
		}
		if(!operation.isEmpty())
			command+=operation;
		if(!param2.isEmpty()) {
			command+=param2;
			expectedParameters.add(param2);
		}
	}

}
