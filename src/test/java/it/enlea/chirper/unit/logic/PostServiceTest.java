package it.enlea.chirper.unit.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.enlea.chirper.logic.service.PostService;
import it.enlea.chirper.logic.service.SocialNetworkService;
import it.enlea.chirper.logic.service.parameter.PostParameters;
import it.enlea.chirper.logic.service.parameter.RequestParametersInterface;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.SessionPostRepository;

class PostServiceTest {

	
	SocialNetworkService command;
	PostRepository postRepository;

	@BeforeEach
	void initCommand() {
		postRepository = new SessionPostRepository();
		command = new PostService(postRepository);
	}
	
	@Test
	void insertAPostShouldReturnEmptyStringAndShouldIncreaseThePostNumbers() {
		String userName = "elsa";
		int previousMessageNum = postRepository.getPostListByUserName(userName).size();
		RequestParametersInterface params = new PostParameters(userName, "let it gooo");
		command.setParameter(params);
		String output = command.execute();
		assertEquals("",output);
		assertEquals(previousMessageNum+1,postRepository.getPostListByUserName(userName).size() );
	}
	
		
	
}
