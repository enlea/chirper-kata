package it.enlea.chirper.logic;

import java.time.Clock;
import java.time.LocalDateTime;

public class TimeManager {
	
	private Clock clock = Clock.systemDefaultZone();
	
	private static TimeManager instance = null;
	
	private TimeManager() {
		
	}
	public static TimeManager getInstance() {
		if (instance == null) {
			instance = new TimeManager();
		}
		return instance;
	}
	
	public LocalDateTime now() {
		return LocalDateTime.now(clock);
	}

	public void setClock(Clock clock) {
		this.clock = clock;
	}
	
	

}
