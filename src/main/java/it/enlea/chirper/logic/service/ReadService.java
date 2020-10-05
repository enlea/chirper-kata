package it.enlea.chirper.logic.service;

import java.util.SortedSet;
import static it.enlea.chirper.logic.ParameterValidator.isValidUsername;

import it.enlea.chirper.logic.ConsoleOutputFormatter;
import it.enlea.chirper.logic.service.parameter.ReadParameters;
import it.enlea.chirper.logic.service.parameter.RequestParametersInterface;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.model.Post;

public class ReadService implements SocialNetworkService {
	private PostRepository repository;
	private ReadParameters parameters;
	
	public ReadService(PostRepository postRepository) {
		this.repository 	= postRepository;
	}
	
	@Override
	public void setParameter(RequestParametersInterface parameters) {
		this.parameters = (ReadParameters) parameters;
		
	}

	@Override
	public String execute() {
		String output = new String();
			String userName = parameters.getUserName();
			if (isValidUsername(userName)) {
				SortedSet<Post> postList = repository.getPostListByUserName(userName);
				output = ConsoleOutputFormatter.formatReadPostList(postList);
			}
		return output;	
	}
}
