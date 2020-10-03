package it.enlea.chirper.logic;

public interface ChirperServiceInterface {
	
	public String postMessage(String userName, String message);
	
	public String read(String userName);
}
