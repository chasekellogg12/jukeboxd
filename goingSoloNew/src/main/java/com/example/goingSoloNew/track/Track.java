package com.example.goingSoloNew.track;

import java.util.List;

import com.example.goingSoloNew.post.Post;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tracks")
public class Track {
    @Id
    @Column(length = 255) // Matching the length defined in the SQL table
    private String trackId;

    @Column(length = 255)
    private String name;

    @Column(length = 255)
    private String artist;

    @Column(length = 255)
    private String album;

    @Column(length = 4)
    private String year;

    @Column(length = 255)
    private String albumCoverArt;
    
	@OneToMany(mappedBy = "postSubject", cascade = CascadeType.REMOVE)
	private List<Post> reviews;
	
	public Track() {
		
	}
	
	public Track(String trackId, String name, String artist, String album, String year, String albumCoverArt) {
		this.trackId = trackId;
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
		this.albumCoverArt = albumCoverArt;
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

	public List<Post> getReviews() {
		return reviews;
	}

	public void setReviews(List<Post> reviews) {
		this.reviews = reviews;
	}
	
}
