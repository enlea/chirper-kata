package it.enlea.chirper.logic.service;

import java.util.SortedSet;
import it.enlea.chirper.logic.ResponseFormatter;
import it.enlea.chirper.logic.service.parameter.ReadParameters;
import it.enlea.chirper.logic.service.parameter.RequestParametersInterface;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.model.Post;

public class ReadService implements SocialNetworkService {
	private PostRepository repository;
	private ReadParameters parameters;
	private ResponseFormatter formatter;
	
	public ReadService(PostRepository postRepository, ResponseFormatter formatter) {
		this.repository 	= postRepository;
		this.formatter		= formatter;
	}
	
	@Override
	public void setParameter(RequestParametersInterface parameters) {
		this.parameters = (ReadParameters) parameters;
		
	}

	@Override
	public String execute() {
		String output = new String();
			String userName = parameters.getUserName();
			SortedSet<Post> postList = repository.getPostListByUserName(userName);
			output = formatter.formatReadPostList(postList);
		return output;	
	}
}
