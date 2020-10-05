package it.enlea.chirper.client;

import it.enlea.chirper.logic.RequestDispatcher;

public class ConsoleClient implements ChirperClientInterface{
	
	private RequestDispatcher commandInvoker 	= null;
	
	public ConsoleClient(RequestDispatcher commandInvoker) {
		this.commandInvoker	= commandInvoker;
	}
	
	@Override
	public String processCommand(String inputCommand) {
		
		return commandInvoker.executeCommand(inputCommand);
	}
	
	

}
