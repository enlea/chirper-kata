package it.enlea.chirper.client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.enlea.chirper.logic.service.parameter.FollowParameters;
import it.enlea.chirper.logic.service.parameter.PostParameters;
import it.enlea.chirper.logic.service.parameter.ReadParameters;
import it.enlea.chirper.logic.service.parameter.RequestParametersInterface;
import it.enlea.chirper.logic.service.parameter.UnknowRequestParameter;
import it.enlea.chirper.logic.service.parameter.WallParameters;

public class ConsoleCommandParser implements InputCommandParser {
	
	final static String postRegEx 	= "(?<username>\\w+)(\\s(->)\\s)(?<message>.+)";
	final static String readRegEx  	="(?<username>\\w+)";
	final static String	followRegEx	="(?<username>\\w+)(\\s(follows)\\s)(?<following>\\w+)";
	final static String wallRegEx	="(?<username>\\w+)(\\s(wall))";

	Pattern regEx;
	Matcher matcher;
	
	public ConsoleCommandParser() {
	}
	
	@Override
	public RequestParametersInterface parseInputCommand(String inputCommand) {
		if(isPostCommand(inputCommand)) 
			return new PostParameters(matcher.group("username"), matcher.group("message"));
		if(isReadCommand(inputCommand)) 
			return new ReadParameters(matcher.group("username"));
		if(isFollowCommand(inputCommand)) 
			return new FollowParameters(matcher.group("username"), matcher.group("following"));
		if(isWallCommand(inputCommand)) 
			return new WallParameters(matcher.group("username"));
		return new UnknowRequestParameter();
		
	}
	
	private boolean isPostCommand(String inputCommand) {
		return isCommand(inputCommand, postRegEx);
	}
	private boolean isReadCommand(String inputCommand) {
		return isCommand(inputCommand, readRegEx);
	}
	
	private boolean isFollowCommand(String inputCommand) {
		return isCommand(inputCommand, followRegEx);
	}
	private boolean isWallCommand(String inputCommand) {
		return isCommand(inputCommand, wallRegEx);
	}
	
	private boolean isCommand(String inputCommand, String postRegEx) {
		regEx 	= Pattern.compile(postRegEx);
		matcher = regEx.matcher(inputCommand);
		return matcher.matches();
	}
	

	

}
