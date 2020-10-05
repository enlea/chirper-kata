package it.enlea.chirper.logic.service.parameter;

import it.enlea.chirper.logic.service.RequestType;

public class UnknowRequestParameter implements RequestParametersInterface {

	RequestType type = null;
	public UnknowRequestParameter() {
		this.type = RequestType.UNKNOW;
	}
	
	@Override
	public RequestType getRequestType() {
		return type;
	}

	@Override
	public String getUserName() {
		return null;
	}

}
