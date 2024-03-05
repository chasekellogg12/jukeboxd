package com.example.goingSoloNew.track;

import jakarta.persistence.Embeddable;

@Embeddable
public class TrackBasicInfo {
	private String trackId;
	private String name;
	private String albumCoverArt;
	private String artist;
	private String year;
	
	public TrackBasicInfo() {
		
	}
	
	public TrackBasicInfo(String trackId, String name, String albumCoverArt, String artist, String year) {
		this.trackId = trackId;
		this.name = name;
		this.albumCoverArt = albumCoverArt;
		this.artist = artist;
		this.year = year;
	}

	public String getTrackId() {
		return trackId;
	}

	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlbumCoverArt() {
		return albumCoverArt;
	}

	public void setAlbumCoverArt(String albumCoverArt) {
		this.albumCoverArt = albumCoverArt;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
}
