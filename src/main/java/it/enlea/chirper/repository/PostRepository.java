package it.enlea.chirper.repository;

import java.util.List;

import it.enlea.chirper.repository.model.Post;

public interface PostRepository {

	Post insertPost(Post post);
		
	List<Post> getPostListByUserName(String userName);
	

}