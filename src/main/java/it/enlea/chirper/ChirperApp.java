package it.enlea.chirper;

import java.util.Scanner;

import it.enlea.chirper.client.ChirperClientInterface;
import it.enlea.chirper.client.ConsoleClient;
import it.enlea.chirper.client.ConsoleCommandParser;
import it.enlea.chirper.client.InputCommandParser;
import it.enlea.chirper.logic.ConsoleResponseFormatter;
import it.enlea.chirper.logic.RequestDispatcher;
import it.enlea.chirper.logic.ResponseFormatter;
import it.enlea.chirper.repository.FollowRepository;
import it.enlea.chirper.repository.PostRepository;
import it.enlea.chirper.repository.SessionFollowRepository;
import it.enlea.chirper.repository.SessionPostRepository;

public class ChirperApp {
	
	private static final String NEW_LINE = "> ";
	private static final String END = "exit";
	
	private ChirperClientInterface client;
	
	public ChirperApp(ChirperClientInterface client) {
		this.client=client;
	}
	
	
	public void start() {
		Scanner scanner = new Scanner(System.in);
        String inputCommand = "";
        while (inputCommand.compareTo(END)!=0) {
            System.out.print(NEW_LINE);
            inputCommand = scanner.nextLine();
            String result= client.processCommand(inputCommand);
            if (!result.isEmpty())
            	System.out.print(result);
            
        }
        scanner.close();
	}

	public static void main(String[] args) {
	   PostRepository postRepository = new SessionPostRepository();
	   FollowRepository followRepository = new SessionFollowRepository();
	   InputCommandParser parser = new ConsoleCommandParser();
	   ResponseFormatter formatter = new ConsoleResponseFormatter();
	   RequestDispatcher invoker = new RequestDispatcher (postRepository,followRepository, parser, formatter);
       ChirperClientInterface client = new ConsoleClient(invoker); 
       ChirperApp application = new ChirperApp(client);
       application.start();
       
	}

}
