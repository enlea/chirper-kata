package it.enlea.chirper.logic.service;

import static it.enlea.chirper.logic.ParameterValidator.isValidUsername;

import java.util.Set;
import java.util.SortedSet;

import it.enlea.chirper.logic.ConsoleOutputFormatter;
import it.enlea.chirper.logic.service.parameter.RequestParametersInterface;
import it.enlea.chirper.logic.service.parameter.WallParameters;
import it.enlea.chirper.repository.FollowRepository;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.model.Post;

public class WallCommand implements SocialNetworkService {

	private FollowRepository followRepository;
	private PostRepository postRepository;
	private WallParameters parameters;
	
	public WallCommand(PostRepository postRepository, FollowRepository followRepository) {
		this.postRepository 	= postRepository;
		this.followRepository	= followRepository;
	}

	@Override
	public void setParameter(RequestParametersInterface parameters) {
		this.parameters = (WallParameters) parameters;
		
	}

	@Override
	public String execute() {
		String userWall = new String();
			String userName = parameters.getUserName();
			if(isValidUsername(userName)) {
				Set<String> usersToShow = followRepository.getFollowingUserByUserName(userName);
				usersToShow.add(userName);
				SortedSet<Post> wallPosts= postRepository.getPostListBySetOfUserName(usersToShow);
				userWall = ConsoleOutputFormatter.formatWallPostList(wallPosts); 
		}
		return userWall;
	}
}
