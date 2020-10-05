package it.enlea.chirper.logic;

import java.time.Clock;
import java.time.LocalDateTime;

public class ChirperTimeManager {
	
	private Clock clock = Clock.systemDefaultZone();
	
	private static ChirperTimeManager instance = null;
	
	private ChirperTimeManager() {
		
	}
	public static ChirperTimeManager getInstance() {
		if (instance == null) {
			instance = new ChirperTimeManager();
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
