package it.enlea.chirper;

import java.util.Scanner;

import it.enlea.chirper.client.ChirperClientInterface;
import it.enlea.chirper.client.ConsoleClient;

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
       ChirperClientInterface client = new ConsoleClient(); 
       ChirperApp application = new ChirperApp(client);
       application.start();
       
	}

}
