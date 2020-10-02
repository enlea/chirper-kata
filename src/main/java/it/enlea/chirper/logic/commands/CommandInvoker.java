package it.enlea.chirper.logic.commands;

import java.util.HashMap;
import java.util.List;

public class CommandInvoker {
	
	private HashMap<CommandType, SocialNetworkCommand> commandMap = new HashMap<CommandType, SocialNetworkCommand>();
	
	public CommandInvoker() {
		commandMap.put(CommandType.POST, new DefaultOperation());
		commandMap.put(CommandType.READ, new DefaultOperation());
		commandMap.put(CommandType.FOLLOW, new DefaultOperation());
		commandMap.put(CommandType.WALL, new DefaultOperation());
		commandMap.put(CommandType.DEFAULT, new DefaultOperation());
	}
	
	public String invokeCommand(CommandType commandKey, List<String> parameters) {
		return commandMap.get(commandKey).execute(parameters);
	}

}
