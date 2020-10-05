package it.enlea.chirper.logic;

import java.util.HashMap;
import it.enlea.chirper.client.InputCommandParser;
import it.enlea.chirper.logic.service.DoNothingService;
import it.enlea.chirper.logic.service.FollowService;
import it.enlea.chirper.logic.service.PostService;
import it.enlea.chirper.logic.service.ReadService;
import it.enlea.chirper.logic.service.RequestType;
import it.enlea.chirper.logic.service.SocialNetworkService;
import it.enlea.chirper.logic.service.WallCommand;
import it.enlea.chirper.logic.service.parameter.RequestParametersInterface;
import it.enlea.chirper.repository.FollowRepository;
import it.enlea.chirper.repository.PostRepository;

public class RequestDispatcher {
	
	private HashMap<RequestType, SocialNetworkService> commandMap = new HashMap<RequestType, SocialNetworkService>();
	
	private InputCommandParser parser;
	
	public RequestDispatcher(PostRepository postRepository, FollowRepository followRepository, InputCommandParser parser, ResponseFormatter formatter) {
		commandMap.put(RequestType.POST, new PostService(postRepository));
		commandMap.put(RequestType.READ, new ReadService(postRepository, formatter));
		commandMap.put(RequestType.FOLLOW, new FollowService(followRepository));
		commandMap.put(RequestType.WALL, new WallCommand(postRepository, followRepository, formatter));
		commandMap.put(RequestType.UNKNOW, new DoNothingService());
		this.parser = parser;
	}
	
	public String executeCommand(String inputCommand) {
		RequestParametersInterface params = parser.parseInputCommand(inputCommand);
		SocialNetworkService command = commandMap.get(params.getRequestType());
		command.setParameter(params);
		return command.execute();
	}

	

}
