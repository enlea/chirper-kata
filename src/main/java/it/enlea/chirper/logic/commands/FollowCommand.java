package it.enlea.chirper.logic.commands;

import static it.enlea.chirper.logic.ParameterValidator.isValidUsername;

import java.util.List;

import it.enlea.chirper.repository.FollowRepository;

public class FollowCommand implements SocialNetworkCommand{

	private FollowRepository repository;
	
	public FollowCommand(FollowRepository repository) {
		this.repository= repository;
	}
	
	@Override
	public String execute(List<String> parameters) {
		if(parameters.size()==2) {
			String username = parameters.get(0);
			String following = parameters.get(1);
			if (isValidUsername(username) && isValidUsername(following)) {
				repository.insertFollowRelationship(username, following);
			}
		}
		return "";
	}

	
}
