package it.enlea.chirper.logic.service.parameter;

import it.enlea.chirper.logic.service.RequestType;

public class WallParameters implements RequestParametersInterface {

	private String userName;
	
	public WallParameters(String userName) {
		this.userName = userName;
	}
	@Override
	public RequestType getRequestType() {
		return RequestType.WALL;
	}

	public String getUserName() {
		return userName;
	}
	


}
