package com.example.goingSoloNew.user;

import java.util.Date;

import jakarta.persistence.Embeddable;

@Embeddable
// userWhoActed...
// 1. created a review of
// 2. followed 
// 3. liked a review by
public class NewActivity {
	private String userWhoActed;
	private String activityString;
	private String thingActedUpon;
	private Date timeCreated;
	
	public NewActivity() {
		
	}
	
	public NewActivity(String userWhoActed, String activityString, String thingActedUpon, Date timeCreated) {
		this.userWhoActed = userWhoActed;
		this.activityString = activityString;
		this.thingActedUpon = thingActedUpon;
		this.timeCreated = timeCreated;
	}
	
	public String getActivityString() {
		return activityString;
	}
	public void setActivityString(String activityString) {
		this.activityString = activityString;
	}
	public Date getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	public String getUserWhoActed() {
		return userWhoActed;
	}

	public void setUserWhoActed(String userWhoActed) {
		this.userWhoActed = userWhoActed;
	}

	public String getThingActedUpon() {
		return thingActedUpon;
	}

	public void setThingActedUpon(String thingActedUpon) {
		this.thingActedUpon = thingActedUpon;
	}
	
}
