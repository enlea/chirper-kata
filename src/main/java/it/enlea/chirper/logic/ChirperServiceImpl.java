package it.enlea.chirper.logic;

import static it.enlea.chirper.logic.ParameterValidator.*;
import java.util.Collections;
import java.util.List;

import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.RepositoryManager;
import it.enlea.chirper.repository.model.Post;

public class ChirperServiceImpl implements ChirperServiceInterface {

	private PostRepository postRepository;
	
	public ChirperServiceImpl (PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	@Override
	public String postMessage(String user, String msg) {
		if (isValidUsername(user) && isValidMessage(msg)) {
			Post post = new Post (user, msg);
			postRepository.insertPost(post);
		}
		return "" ;
	}

	@Override
	public String read(String userName) {
		List<Post> postList = postRepository.getPostListByUserName(userName);
		Collections.reverse(postList);
		String output = ConsoleFormatter.formatPostList(postList);
		return output;
	}

}
