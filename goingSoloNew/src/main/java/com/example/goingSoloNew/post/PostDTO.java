package com.example.goingSoloNew.post;

import java.util.Date;
import java.util.List;

import com.example.goingSoloNew.track.TrackBasicInfo;
import com.example.goingSoloNew.user.BasicUserInfo;
import com.example.goingSoloNew.user.MyUserDTO;

public class PostDTO {
	private Long postId;
	private MyUserDTO author;
	private String text;
	private Date datePosted;
	private List<MyUserDTO> usernamesWhoLiked;
	private List<String> stringUsernamesWhoLiked;
	private TrackBasicInfo postSubjectBasicInfo;
	// should have just a list of usernames who liked as strings
	
	public PostDTO() {
		
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public MyUserDTO getAuthor() {
		return author;
	}

	public void setAuthor(MyUserDTO author) {
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

	public List<MyUserDTO> getUsernamesWhoLiked() {
		return usernamesWhoLiked;
	}

	public void setUsernamesWhoLiked(List<MyUserDTO> usernamesWhoLiked) {
		this.usernamesWhoLiked = usernamesWhoLiked;
	}

	public List<String> getStringUsernamesWhoLiked() {
		return stringUsernamesWhoLiked;
	}

	public void setStringUsernamesWhoLiked(List<String> stringUsernamesWhoLiked) {
		this.stringUsernamesWhoLiked = stringUsernamesWhoLiked;
	}

	public TrackBasicInfo getPostSubjectBasicInfo() {
		return postSubjectBasicInfo;
	}

	public void setPostSubjectBasicInfo(TrackBasicInfo postSubjectBasicInfo) {
		this.postSubjectBasicInfo = postSubjectBasicInfo;
	}
	
	
	
}
