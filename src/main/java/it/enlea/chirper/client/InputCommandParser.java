package it.enlea.chirper.client;

import it.enlea.chirper.logic.service.parameter.RequestParametersInterface;

public interface InputCommandParser {

	RequestParametersInterface parseInputCommand(String inputCommand);

}