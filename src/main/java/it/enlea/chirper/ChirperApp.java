package it.enlea.chirper;

import java.util.Scanner;

import it.enlea.chirper.client.ChirperClientInterface;
import it.enlea.chirper.client.ConsoleClient;
import it.enlea.chirper.client.InputCommandParser;
import it.enlea.chirper.logic.commands.CommandInvoker;
import it.enlea.chirper.repository.FollowRepository;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.SessionFollowRepository;
import it.enlea.chirper.repository.SessionPostRepository;

public class ChirperApp {
	
	private static final String NEW_LINE = "> ";
	
	private ChirperClientInterface client;
	
	public ChirperApp(ChirperClientInterface client) {
		this.client=client;
	}
	
	
	public void start() {
		Scanner scanner = new Scanner(System.in);
        String inputCommand = "";
        while (true) {
            System.out.print(NEW_LINE);
            inputCommand = scanner.nextLine();
            String result= client.processCommand(inputCommand);
            if (!result.isEmpty())
            	System.out.println(NEW_LINE+result);
            
        }
	}

	public static void main(String[] args) {
	   PostRepository postRepository = new SessionPostRepository();
	   FollowRepository followRepository = new SessionFollowRepository();
	   CommandInvoker invoker = new CommandInvoker (postRepository,followRepository);
	   InputCommandParser parser = new InputCommandParser();
       ChirperClientInterface client = new ConsoleClient(invoker, parser); 
       ChirperApp application = new ChirperApp(client);
       application.start();
       
	}

}
