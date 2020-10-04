package it.enlea.chirper.logic.commands;

import java.util.List;
import java.util.SortedSet;
import static it.enlea.chirper.logic.ParameterValidator.isValidUsername;

import it.enlea.chirper.logic.ConsoleOutputFormatter;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.model.Post;

public class ReadCommand implements SocialNetworkCommand {
	private PostRepository repository;
	
	public ReadCommand(PostRepository postRepository) {
		this.repository 	= postRepository;
	}
	
	
	@Override
	public String execute(List<String> parameters) {
		String output = new String();
		if(parameters.size()==1) {
			String userName = parameters.get(0);
			if (isValidUsername(userName)) {
				SortedSet<Post> postList = repository.getPostListByUserName(userName);
				output = ConsoleOutputFormatter.formatReadPostList(postList);
			}
		}
		return output;	
	}

}
