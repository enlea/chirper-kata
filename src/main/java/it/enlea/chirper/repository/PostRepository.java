package it.enlea.chirper.repository;

import java.util.Set;
import java.util.SortedSet;

import it.enlea.chirper.repository.model.Post;

public interface PostRepository {

	void insertPost(Post post);
		
	SortedSet<Post> getPostListByUserName(String userName);
	
	SortedSet<Post> getPostListBySetOfUserName(Set<String> userNameSet);

}