package it.enlea.chirper.unit.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.enlea.chirper.repository.SessionPostRepository;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.model.Post;

class PostRepositoryTest {
	
	private PostRepository repository;
	
	private List<Post> examplePostList;

	@BeforeEach
	void setUp() throws Exception {
		repository = new SessionPostRepository();
		initExamplePostList();
	}
	
	private void initExamplePostList() {
		examplePostList = new ArrayList<Post>();
		examplePostList.add(new Post("alice","hello!"));
		examplePostList.add(new Post("alice","I'm here!"));
		examplePostList.add(new Post("bob","See you!"));
	}

//
	@Test
	void insertPostShouldReturnThePost() {
		Post expected 		= new Post("alice", "hello world!");
		Post output = repository.insertPost(expected);
		assertEquals(expected,output);
	}
	
	@Test
	void insertANullPostShouldReturnNull() {
		Post output = repository.insertPost(null);
		assertEquals(null, output);
	}
	
	@Test
	void getPostListByUserNameShouldReturnTheUserList() throws InterruptedException {
		List<Post> expected = new ArrayList<Post>();
		expected.add(new Post("alice","hello!"));
		expected.add(new Post("alice","I'm here!"));
		insertMessagesWithDelay(expected,100);
		repository.insertPost(new Post("anna", "bye"));
		List<Post> output = repository.getPostListByUserName("alice");
		assertEquals(expected, output);
	}
	
	@Test
	void getPostListByUserNameOfAnUnkowUserShuouldReturnAnEmptyList() throws InterruptedException {
		insertMessagesWithDelay(examplePostList,100);
		assertEquals(new ArrayList<Post>(),repository.getPostListByUserName("charlie") );
	}
	
	void insertMessagesWithDelay(List<Post> list, int milliseconds) throws InterruptedException {
		for (Post p : list) {
			Thread.sleep(milliseconds);
			repository.insertPost(p);
		}
	}
	
	

}
