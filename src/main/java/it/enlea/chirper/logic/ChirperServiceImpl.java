package it.enlea.chirper.logic;

import static it.enlea.chirper.logic.ParameterValidator.*;
import java.util.Set;
import java.util.SortedSet;

import it.enlea.chirper.repository.FollowRepository;
import it.enlea.chirper.repository.PostRepository;
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
		SortedSet<Post> postList = postRepository.getPostListByUserName(userName);
		String output = ConsoleOutputFormatter.formatReadPostList(postList);
		return output;
	}

	@Override
	public String follows(String userName, String following) {
		if (isValidUsername(userName) && isValidUsername(following)) {
			followRepository.insertFollowRelationship(userName, following);
		}
		return "";
	}

	@Override
	public String wall(String userName) {
		String userWall = new String();
		if(isValidUsername(userName)) {
			Set<String> usersToShow = followRepository.getFollowingUserByUserName(userName);
			usersToShow.add(userName);
			SortedSet<Post> wallPosts= postRepository.getPostListBySetOfUserName(usersToShow);
			userWall = ConsoleOutputFormatter.formatWallPostList(wallPosts); 
		}
		return userWall;
	}

}
