package com.example.goingSoloNew.user;

public class FollowRequest {
	private String follower;
	private String followed;
	
	public FollowRequest() {
		
	}

	public String getFollower() {
		return follower;
	}

	public void setFollower(String follower) {
		this.follower = follower;
	}

	public String getFollowed() {
		return followed;
	}

	public void setFollowed(String followed) {
		this.followed = followed;
	}
}
