package it.enlea.chirper.logic.service.parameter;

import it.enlea.chirper.logic.service.RequestType;

public class ReadParameters implements RequestParametersInterface {

	private String userName;
	
	public ReadParameters(String userName) {
		this.userName = userName;
	}
	@Override
	public RequestType getRequestType() {
		return RequestType.READ;
	}
	@Override
	public String getUserName() {
		return userName;
	}
	


}
