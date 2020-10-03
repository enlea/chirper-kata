package it.enlea.chirper.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParameterValidator {
	private static List<String> reservedWord = new ArrayList<String>(Arrays.asList("wall", "follows", "->")); 
	
	public static boolean isValidMessage(String message) {
		return message!=null &&!message.isEmpty();
	}

	public static boolean isValidUsername(String userName) {
		return userName!=null && !userName.isEmpty() && isNotAReservedWord(userName);
	}
	
	public static boolean isNotAReservedWord(String word) {
		
		return !reservedWord.contains(word.trim()) ;
	}
}
