package it.enlea.chirper.logic;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedSet;

import it.enlea.chirper.repository.model.Post;

public class ConsoleOutputFormatter {
	
	
	private static String POST_READ_PATTERN ="> %s (%d minutes ago)\n";
	private static String POST_WALL_PATTERN ="> %s - %s (%d minutes ago)\n";
	
	
	public static String formatReadPostList(SortedSet<Post> postList) {
		StringBuilder sb = new StringBuilder();
		postList.forEach(p-> sb.append(formatPostForReadResponse(p)));
		return sb.toString();
	}
	
	public static String formatWallPostList(SortedSet<Post> postList) {
		StringBuilder sb = new StringBuilder();
		postList.forEach(p-> sb.append(formatPostForWallResponse(p)));
		return sb.toString();
	}

	private static String formatPostForReadResponse( Post post) {
		Duration agoDuration = Duration.between(post.getTimeStamp(), LocalDateTime.now());
		String output = String.format(POST_READ_PATTERN, post.getMessage(), agoDuration.toMinutes());
		return output;
	}

	private static String formatPostForWallResponse(Post post) {
		Duration agoDuration = Duration.between(post.getTimeStamp(), LocalDateTime.now());
		String output = String.format(POST_WALL_PATTERN,post.getUserName(), post.getMessage(), agoDuration.toMinutes());
		return output;
	}

}
