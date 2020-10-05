package it.enlea.chirper.logic.service;

import it.enlea.chirper.logic.service.parameter.RequestParametersInterface;

public class DoNothingService implements SocialNetworkService {
	RequestParametersInterface parameters;
	

	
	@Override
	public String execute() {
		return new String();
	}

	@Override
	public void setParameter(RequestParametersInterface parameters) {
		this.parameters = parameters;
		
	}

}
