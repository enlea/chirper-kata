package it.enlea.chirper.logic;

import static it.enlea.chirper.logic.ParameterValidator.*;
import java.util.Collections;
import java.util.List;

import it.enlea.chirper.repository.FollowRepository;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.RepositoryManager;
import it.enlea.chirper.repository.model.Post;

public class ChirperServiceImpl implements ChirperServiceInterface {

	private PostRepository postRepository;
	private FollowRepository followRepository;
	
	public ChirperServiceImpl (PostRepository postRepository, FollowRepository followRepository) {
		this.postRepository 	= postRepository;
		this.followRepository 	= followRepository;
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
		String output = ConsoleOutputFormatter.formatPostList(postList);
		return output;
	}

	@Override
	public String follows(String userName, String following) {
		if (isValidUsername(userName) && isValidUsername(following)) {
			followRepository.insertFollowRelationship(userName, following);
		}
		return "";
	}

}
