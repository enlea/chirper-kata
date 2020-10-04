package it.enlea.chirper.logic.commands;

import static it.enlea.chirper.logic.ParameterValidator.isValidUsername;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import it.enlea.chirper.logic.ConsoleOutputFormatter;
import it.enlea.chirper.repository.FollowRepository;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.model.Post;

public class WallCommand implements SocialNetworkCommand {

	private FollowRepository followRepository;
	private PostRepository postRepository;
	
	public WallCommand(PostRepository postRepository, FollowRepository followRepository) {
		this.postRepository 	= postRepository;
		this.followRepository	= followRepository;
	}

	@Override
	public String execute(List<String> parameters) {
		String userWall = new String();
		if(parameters.size()==1) {
			String userName = parameters.get(0);
			if(isValidUsername(userName)) {
				Set<String> usersToShow = followRepository.getFollowingUserByUserName(userName);
				usersToShow.add(userName);
				SortedSet<Post> wallPosts= postRepository.getPostListBySetOfUserName(usersToShow);
				userWall = ConsoleOutputFormatter.formatWallPostList(wallPosts); 
			}
		}
		return userWall;
	}

}
