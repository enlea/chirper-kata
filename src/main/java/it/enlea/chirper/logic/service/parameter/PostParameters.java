package it.enlea.chirper.logic.service.parameter;

import it.enlea.chirper.logic.service.RequestType;

public class PostParameters implements RequestParametersInterface {

	private String userName;
	private String message;
	
	public PostParameters(String userName, String message) {
		this.userName = userName;
		this.message = message;
	}
	@Override
	public RequestType getRequestType() {
		return RequestType.POST;
	}
	@Override
	public String getUserName() {
		return userName;
	}
	public String getMessage() {
		return message;
	}

}
