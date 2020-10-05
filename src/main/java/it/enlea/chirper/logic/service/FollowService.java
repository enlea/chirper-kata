package it.enlea.chirper.logic.service;

import static it.enlea.chirper.logic.ParameterValidator.isValidUsername;

import it.enlea.chirper.logic.service.parameter.FollowParameters;
import it.enlea.chirper.logic.service.parameter.RequestParametersInterface;
import it.enlea.chirper.repository.FollowRepository;

public class FollowService implements SocialNetworkService{

	private FollowRepository repository;
	FollowParameters parameters;
	
	public FollowService(FollowRepository repository) {
		this.repository= repository;
	}

	@Override
	public void setParameter(RequestParametersInterface parameters) {
		this.parameters = (FollowParameters) parameters;
	}

	@Override
	public String execute() {
		if (isValidUsername(parameters.getUserName()) && isValidUsername(parameters.getFollowing())) {
			repository.insertFollowRelationship(parameters.getUserName(), parameters.getFollowing());
		}
		return "";
	}

	
}
