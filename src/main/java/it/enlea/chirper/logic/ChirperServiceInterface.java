package it.enlea.chirper.logic;

public interface ChirperServiceInterface {
	
	public String postMessage(String userName, String message);
	
	public String read(String userName);
	
	public String follows(String userName, String following);
	
	public String wall(String userName);
}
