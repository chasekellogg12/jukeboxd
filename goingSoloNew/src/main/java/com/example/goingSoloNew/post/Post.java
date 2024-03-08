package com.example.goingSoloNew.post;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.goingSoloNew.myLike.MyLike;
import com.example.goingSoloNew.track.Track;
import com.example.goingSoloNew.user.MyUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;
	
    @ManyToOne
    @JoinColumn(name = "author_username")
	private MyUser author;
	
	@Column(columnDefinition = "TEXT")
    private String text;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datePosted;
	
    @OneToMany(mappedBy = "likedPost", cascade = CascadeType.REMOVE)
    private List<MyLike> likes;
    
    @ManyToOne
    @JoinColumn(name = "track_id")
    private Track postSubject;
    
    @Column
    private int rating;  
	
	public Post() {
		
	}
	
	public Post(MyUser author, String text, Track subject) {
		this.author = author;
		this.text = text;
		Calendar c = Calendar.getInstance();
		this.datePosted = c.getTime();
		this.postSubject = subject;
		this.rating = 0;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public MyUser getAuthor() {
		return author;
	}

	public void setAuthor(MyUser author) {
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}

	public List<MyLike> getLikes() {
		return likes;
	}

	public void setLikes(List<MyLike> likes) {
		this.likes = likes;
	}

	public Track getPostSubject() {
		return postSubject;
	}

	public void setPostSubject(Track postSubject) {
		this.postSubject = postSubject;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	
}
