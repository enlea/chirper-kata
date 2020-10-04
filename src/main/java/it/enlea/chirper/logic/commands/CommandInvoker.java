package it.enlea.chirper.logic.commands;

import java.util.HashMap;
import java.util.List;

import it.enlea.chirper.repository.FollowRepository;
import it.enlea.chirper.repository.PostRepository;

public class CommandInvoker {
	
	private HashMap<CommandType, SocialNetworkCommand> commandMap = new HashMap<CommandType, SocialNetworkCommand>();
	
	public CommandInvoker(PostRepository postRepository, FollowRepository followRepository) {
		commandMap.put(CommandType.POST, new PostCommand(postRepository));
		commandMap.put(CommandType.READ, new ReadCommand(postRepository));
		commandMap.put(CommandType.FOLLOW, new FollowCommand(followRepository));
		commandMap.put(CommandType.WALL, new WallCommand(postRepository, followRepository));
		commandMap.put(CommandType.DEFAULT, new DefaultOperation());
	}
	

	public String invokeCommand(CommandType commandKey, List<String> parameters) {
		return commandMap.get(commandKey).execute(parameters);
	}

}
