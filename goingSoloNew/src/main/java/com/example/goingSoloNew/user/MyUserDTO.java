package com.example.goingSoloNew.user;

import java.util.List;

import com.example.goingSoloNew.track.TrackBasicInfo;

public class MyUserDTO {
	
	private String username;
	private String name;
	private String email;
	private String avatar;
	// instead of being a list of Strings, these should be a list of basicInfo, which is a class containing just name, username, and profile picture 
	private List<BasicUserInfo> followersBasicInfo;
	private List<BasicUserInfo> followingBasicInfo;
	private List<String> topFourSongs;
	private List<NewActivity> recentActivity;
	private TrackBasicInfo[] topFour;
	
	
	public MyUserDTO() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<BasicUserInfo> getFollowersBasicInfo() {
		return followersBasicInfo;
	}

	public void setFollowersBasicInfo(List<BasicUserInfo> followersBasicInfo) {
		this.followersBasicInfo = followersBasicInfo;
	}

	public List<BasicUserInfo> getFollowingBasicInfo() {
		return followingBasicInfo;
	}

	public void setFollowingBasicInfo(List<BasicUserInfo> followingBasicInfo) {
		this.followingBasicInfo = followingBasicInfo;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<String> getTopFourSongs() {
		return topFourSongs;
	}

	public void setTopFourSongs(List<String> topFourSongs) {
		this.topFourSongs = topFourSongs;
	}

	public List<NewActivity> getRecentActivity() {
		return recentActivity;
	}

	public void setRecentActivity(List<NewActivity> recentActivity) {
		this.recentActivity = recentActivity;
	}

	public TrackBasicInfo[] getTopFour() {
		return topFour;
	}

	public void setTopFour(TrackBasicInfo[] topFour) {
		this.topFour = topFour;
	}
	
	

}
