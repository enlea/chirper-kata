package it.enlea.chirper.client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import it.enlea.chirper.logic.commands.CommandType;

public class InputCommandParser {
	
	private CommandType command;
	private List<String> parameters;
	
	private static final String POST_CMD 	=" -> ";
	private static final String FOLLOW_CMD 	=" follows ";
	private static final String WALL_CMD	=" wall";

	
	public InputCommandParser() {
		init();
	}
	
	private void init() {
		this.command = CommandType.DEFAULT;
		this.parameters = new ArrayList<String>();
	}
	
	public void parseCommand(String inputCommand) {
		init();
		String postRegEx 	= "(\\w+)(\\s(->)\\s)(.+)";
		String readRegEx  	="(\\w+)";
		String followRegEx	="(\\w+)(\\s(follows)\\s)(\\w+)";
		String wallRegEx	="(\\w+)(\\s(wall))";
		if(Pattern.matches(postRegEx, inputCommand)) {
			String user= inputCommand.substring(0, inputCommand.indexOf(POST_CMD));
			String msg = inputCommand.substring(user.length()+POST_CMD.length());
			parameters.add(user);
			parameters.add(msg);
			command = CommandType.POST;
		}
		else if(Pattern.matches(readRegEx, inputCommand)){
			parameters.add(inputCommand);
			command = CommandType.READ;
		}
		else if(Pattern.matches(followRegEx, inputCommand)) {
			String user= inputCommand.substring(0, inputCommand.indexOf(FOLLOW_CMD));
			String followed = inputCommand.substring(user.length()+FOLLOW_CMD.length());
			parameters.add(user);
			parameters.add(followed);
			command = CommandType.FOLLOW;
		}
		else if(Pattern.matches(wallRegEx, inputCommand)) {
			String user= inputCommand.substring(0, inputCommand.indexOf(WALL_CMD));
			parameters.add(user);
			command = CommandType.WALL;
		}
		else {
			command = CommandType.DEFAULT;
		}
	}
	
	public boolean isPostCommand() {
		return command.equals(CommandType.POST);
	}
	
	public CommandType getCommandType() {
		return command;
	}
	
	public List<String> getParameters(){
		return this.parameters;
	}
	
	
	
	

}
