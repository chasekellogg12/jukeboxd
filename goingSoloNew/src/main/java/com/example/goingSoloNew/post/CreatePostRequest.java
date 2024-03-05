package com.example.goingSoloNew.post;

public class CreatePostRequest {
	private String postText;
	private String id;
	private String name;
	private String artist;
	private String album;
	private String year;
	private String albumCoverArt;
	
	public CreatePostRequest() {
		
	}
	
	public CreatePostRequest(String postText) {
		this.postText = postText;
	}
	
	public String getPostText() {
		return this.postText;
	}
	
	public void setPostText(String postText) {
		this.postText = postText;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getAlbumCoverArt() {
		return albumCoverArt;
	}

	public void setAlbumCoverArt(String albumCoverArt) {
		this.albumCoverArt = albumCoverArt;
	}
	
	
}
