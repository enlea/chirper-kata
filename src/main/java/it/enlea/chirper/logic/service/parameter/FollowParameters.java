package it.enlea.chirper.logic.service.parameter;

import it.enlea.chirper.logic.service.RequestType;

public class FollowParameters implements RequestParametersInterface {

	private String userName;
	private String following;
	
	public FollowParameters(String userName, String following) {
		this.userName = userName;
		this.following = following;
	}
	@Override
	public RequestType getRequestType() {
		return RequestType.FOLLOW;
	}
	@Override
	public String getUserName() {
		return userName;
	}
	public String getFollowing() {
		return following;
	}


}
