package it.enlea.chirper.logic.service.parameter;

import it.enlea.chirper.logic.service.RequestType;

public interface RequestParametersInterface {
	public RequestType getRequestType();
	public String getUserName();
}
