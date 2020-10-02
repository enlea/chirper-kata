package it.enlea.chirper.client;

import it.enlea.chirper.logic.commands.CommandInvoker;

public class ConsoleClient implements ChirperClientInterface{
	private CommandInvoker commandInvoker 	= null;
	private InputCommandParser parser		= null;
	
	public ConsoleClient() {
		commandInvoker 	= new CommandInvoker();
		parser 			= new InputCommandParser();
	}
	
	@Override
	public String processCommand(String inputCommand) {
		parser.parseCommand(inputCommand);
		return commandInvoker.invokeCommand(parser.getCommandType(), parser.getParameters());
	}

}
