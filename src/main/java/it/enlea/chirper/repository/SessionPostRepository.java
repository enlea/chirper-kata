package it.enlea.chirper.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import it.enlea.chirper.repository.model.Post;

public class SessionPostRepository implements PostRepository {
	
	private HashMap<String, List<Post>> postMap; 

	public SessionPostRepository() {
		this.postMap = new HashMap<String, List<Post>>();
	}

	@Override
	public Post insertPost(Post post) {
		if(post!=null) {
			getPostListByUserName(post.getUserName()).add(post);
			return post;
		}   
		return null;
	}

	@Override
	public List<Post> getPostListByUserName(String userName){
		if(!postMap.containsKey(userName)) {
			postMap.put(userName, new ArrayList<Post>());
		}
		return postMap.get(userName);
	}

  


}
