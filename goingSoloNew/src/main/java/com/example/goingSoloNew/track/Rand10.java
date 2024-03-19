package com.example.goingSoloNew.track;

public class Rand10 {
	private float averageRating;
	private TrackBasicInfo track;
	
	public Rand10 (float averageRating, TrackBasicInfo track) {
		this.averageRating = averageRating;
		this.track = track;
	}
	
	public float getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(float averageRating) {
		this.averageRating = averageRating;
	}
	public TrackBasicInfo getTrack() {
		return track;
	}
	public void setTrack(TrackBasicInfo track) {
		this.track = track;
	}
	
}
