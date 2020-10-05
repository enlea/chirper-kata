package it.enlea.chirper.logic;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.SortedSet;

import it.enlea.chirper.repository.model.Post;

public class ConsoleResponseFormatter implements ResponseFormatter {
	
	
	
	private static String AGO_PATTERN		= "(%d %s ago)";
	private static String POST_READ_PATTERN ="> %s %s\n";
	private static String POST_WALL_PATTERN ="> %s - %s %s\n";
	
	public ConsoleResponseFormatter () {
		
	}
	
	@Override
	public String formatReadPostList(SortedSet<Post> postList) {
		StringBuilder sb = new StringBuilder();
		postList.forEach(p-> sb.append(formatPostForReadResponse(p)));
		return sb.toString();
	}
	
	@Override
	public String formatWallPostList(SortedSet<Post> postList) {
		StringBuilder sb = new StringBuilder();
		postList.forEach(p-> sb.append(formatPostForWallResponse(p)));
		return sb.toString();
	}

	private String formatPostForReadResponse( Post post) {
		String output = String.format(POST_READ_PATTERN, post.getMessage(), resolveAgoPattern(post));
		return output;
	}

	private String formatPostForWallResponse(Post post) {
		String output = String.format(POST_WALL_PATTERN,post.getUserName(), post.getMessage(), resolveAgoPattern(post));
		return output;
	}
	
	private String resolveAgoPattern(Post post) {
		Duration agoDuration = Duration.between(post.getTimeStamp(), TimeManager.getInstance().now());
		long duration = 0;
		String unit = "second";
		
		if(agoDuration.toDays()>0) {
			duration = agoDuration.toDays();
			unit= "day";
		}
		else if (agoDuration.toHours()>0) {
			duration = agoDuration.toHours();
			unit= "hour";
		}
		else if(agoDuration.toMinutes()>0) {
			duration = agoDuration.toMinutes();
			unit= "minute";
		}
		else {
			duration = agoDuration.toSeconds();
		}
		unit += duration>1 ?"s":"";
		
		return String.format(AGO_PATTERN, duration, unit);
	}

}
