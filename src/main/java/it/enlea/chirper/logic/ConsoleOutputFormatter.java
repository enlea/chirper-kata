package it.enlea.chirper.logic;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import it.enlea.chirper.repository.model.Post;

public class ConsoleFormatter {
	
	
	private static String POST_PATTERN ="> %s (%d minutes ago)\n";
	
	public static String formatPostList(List<Post> postList) {
		
		String output = new String ();
		LocalDateTime now = LocalDateTime.now();
		for (Post post: postList) {
			
			Duration agoDuration = Duration.between(post.getTimeStamp(), now);
			output += String.format(POST_PATTERN, post.getMessage(), agoDuration.toMinutes());
			
		}
		return output;
		
	}

}
