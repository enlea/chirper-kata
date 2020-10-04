package it.enlea.chirper.repository.model;

import java.time.LocalDateTime;

public class Post implements Comparable<Post> {
	
	private String userName;
	private String message;
	private LocalDateTime timeStamp;
	
	
	public Post(String userName, String message) {
		this(userName, message, LocalDateTime.now());
	}

	public Post(String userName, String message, LocalDateTime timeStamp) {
		this.userName	= userName;
		this.message	= message;
		this.timeStamp 	= timeStamp;
	}

	public String getUserName() {
		return userName;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	} 
	
	@Override
	public int compareTo(Post o) {
		if (getTimeStamp().isBefore(o.getTimeStamp()))
			return 1;
		else if(getTimeStamp().isAfter(o.getTimeStamp())) 
			return -1;
		else 
			return 0;
	}
	

}
