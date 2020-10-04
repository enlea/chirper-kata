package it.enlea.chirper.logic.commands;

import static it.enlea.chirper.logic.ParameterValidator.isValidMessage;
import static it.enlea.chirper.logic.ParameterValidator.isValidUsername;

import java.util.List;

import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.model.Post;

public class PostCommand implements SocialNetworkCommand {
	PostRepository repository;
	
	public PostCommand(PostRepository repository) {
		this.repository = repository;
	}

	@Override
	public String execute(List<String> parameters) {
		if(parameters.size()==2) {
			String username = parameters.get(0);
			String message = parameters.get(1);
			if (isValidUsername(username) && isValidMessage(message)) {
				Post post = new Post (username, message);
				repository.insertPost(post);
			}
		}
		
		return "";
	}

}
