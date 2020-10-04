package it.enlea.chirper.unit.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.enlea.chirper.logic.commands.PostCommand;
import it.enlea.chirper.logic.commands.SocialNetworkCommand;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.SessionPostRepository;

class PostCommandTest {

	
	SocialNetworkCommand command;
	PostRepository postRepository;

	@BeforeEach
	void initCommand() {
		postRepository = new SessionPostRepository();
		command = new PostCommand(postRepository);
	}

	@Test
	void insertAPostShouldReturnEmptyString() {
		List<String> parameters = Arrays.asList("elsa", "let it gooo");
		String output = command.execute(parameters);
		assertEquals("",output);
	}
	
	@Test
	void insertAnEmpityPostShouldReturnEmptyStringAndShuldNotAddMessage() {
		String userName = "elsa";
		int previousMessageNum = postRepository.getPostListByUserName(userName).size();
		List<String> parameters = Arrays.asList(userName, "");
		String output = command.execute(parameters);
		assertEquals("",output);
		assertEquals(previousMessageNum,postRepository.getPostListByUserName(userName).size() );
	}
	
	
}
