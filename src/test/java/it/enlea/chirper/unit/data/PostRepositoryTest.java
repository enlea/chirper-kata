package it.enlea.chirper.unit.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.enlea.chirper.repository.SessionPostRepository;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.model.Post;


class PostRepositoryTest {
	
	private PostRepository repository;
	
	private SortedSet<Post> examplePostList;

	@BeforeEach
	void setUp() throws Exception {
		repository = new SessionPostRepository();
		initExamplePostList();
	}
	
	@Test
	void insertPostShouldInsertThePostInThePostRepository() {
		Post newPost 		= new Post("alice", "hello world!");
		repository.insertPost(newPost);
		SortedSet<Post>  output = repository.getPostListByUserName("alice");
		assertTrue(output.contains(newPost));
	}
	
	@Test
	void insertANullPostShouldNotModifyTheRepository() throws InterruptedException {
		SortedSet<Post> alicePosts = PostTestUtils.filterPostsByUserName(examplePostList,"alice");
		insertMessagesWithDelay(alicePosts , 100);
		repository.insertPost(null);
		SortedSet<Post> output = repository.getPostListByUserName("alice");
		assertEquals(alicePosts.size(), output.size());
	}
	
	@Test
	void getPostListByUserNameShouldReturnTheUserList() throws InterruptedException {
		String username = "alice";
		SortedSet<Post> expected = PostTestUtils.filterPostsByUserName(examplePostList,username);
		insertMessagesWithDelay(examplePostList,100);

		SortedSet<Post> output = repository.getPostListByUserName(username);
		assertEquals(expected, output);
	}

	
	@Test
	void getPostListByUserNameOfAnUnkowUserShuouldReturnAnEmptyList() throws InterruptedException {
		insertMessagesWithDelay(examplePostList,100);
		assertEquals(new TreeSet<Post>(),repository.getPostListByUserName("charlie") );
	}
	
	@Test
	void  getPostListBySetOfUserNameShouldReturnTheUsersPostList() throws InterruptedException{
		insertMessagesWithDelay(examplePostList,100);
		Set<String> users = new HashSet<String> (Arrays.asList("alice", "bob"));
		SortedSet<Post> expectedList = new TreeSet<Post>();
		users.forEach(u -> expectedList.addAll(PostTestUtils.filterPostsByUserName(examplePostList,u)));
		
		SortedSet<Post> output 	= repository.getPostListBySetOfUserName(users);
		assertEquals(expectedList, output);
	}
	
	@Test
	void  getPostListBySetOfSomeUnknowUserNameShouldReturnTheKnownUsersPostList() throws InterruptedException{
		insertMessagesWithDelay(examplePostList,100);
		Set<String> users = new HashSet<String> (Arrays.asList("alice", "bob","ernest"));
		
		SortedSet<Post> expectedList = new TreeSet<Post>();
		users.forEach(u -> expectedList.addAll(PostTestUtils.filterPostsByUserName(examplePostList,u)));
		
		SortedSet<Post> output 	= repository.getPostListBySetOfUserName(users);
		assertEquals(expectedList, output);
	}
	
	@Test
	void getPostListBySetOfUnknowUserNameShouldReturnEmptyList() throws InterruptedException {
		insertMessagesWithDelay(examplePostList,100);
		Set<String> users = new HashSet<String> (Arrays.asList("david", "ernest"));
		SortedSet<Post> output 	= repository.getPostListBySetOfUserName(users);
		assertTrue(output.isEmpty());
	}
	
	private void insertMessagesWithDelay(SortedSet<Post> list, int milliseconds) throws InterruptedException {
		for (Post p : list) {
			Thread.sleep(milliseconds);
			repository.insertPost(p);
		}
	}
	
	private void initExamplePostList() {
		examplePostList = new TreeSet<Post>();
		examplePostList.add(new Post("alice","hello!"));
		examplePostList.add(new Post("alice","I'm here!"));
		examplePostList.add(new Post("bob","See you!"));
	}
	



}
