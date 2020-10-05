package it.enlea.chirper.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.enlea.chirper.repository.model.Post;

public class SessionPostRepository implements PostRepository {
	
	private Map<String, SortedSet<Post>> postMap; 
		
	public SessionPostRepository() {
		this.postMap = new HashMap<String, SortedSet<Post>>();
	}

	@Override
	public void insertPost(Post post) {
		if(post!=null) {
			getPostListByUserName(post.getUserName()).add(post);
		}   
	}

	@Override
	public SortedSet<Post> getPostListByUserName(String userName){
		if(!postMap.containsKey(userName)) {
			postMap.put(userName, new TreeSet<Post>());
		}
		return postMap.get(userName);
	}

	@Override
	public SortedSet<Post> getPostListBySetOfUserName(Set<String> userNameSet){
		SortedSet<Post> postList = new TreeSet<Post>();
		userNameSet.forEach(u -> postList.addAll(getPostListByUserName(u)));
		return postList;
	}

  


}
