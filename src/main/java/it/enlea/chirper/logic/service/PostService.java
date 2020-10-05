package it.enlea.chirper.logic.service;

import it.enlea.chirper.logic.service.parameter.PostParameters;
import it.enlea.chirper.logic.service.parameter.RequestParametersInterface;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.model.Post;

public class PostService implements SocialNetworkService {
	PostRepository repository;
	private PostParameters parameters;
	
	public PostService(PostRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void setParameter(RequestParametersInterface parameters) {
		this.parameters = (PostParameters) parameters;
		
	}
	@Override
	public String execute() {
			String username = parameters.getUserName();
			String message = parameters.getMessage();
			Post post = new Post (username, message);
			repository.insertPost(post);
		return "";
	}

}
