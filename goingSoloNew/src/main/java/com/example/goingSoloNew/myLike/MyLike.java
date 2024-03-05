package com.example.goingSoloNew.myLike;

import com.example.goingSoloNew.post.Post;
import com.example.goingSoloNew.user.MyUser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "my_likes")
public class MyLike {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long likeId;
	
    @ManyToOne
    @JoinColumn(name = "user_username")
    private MyUser liker;
	
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post likedPost;

	public MyLike() {
		
	}
    
    public Long getLikeId() {
		return likeId;
	}

	public void setLikeId(Long likeId) {
		this.likeId = likeId;
	}

	public MyUser getLiker() {
		return liker;
	}

	public void setLiker(MyUser liker) {
		this.liker = liker;
	}

	public Post getLikedPost() {
		return likedPost;
	}

	public void setLikedPost(Post likedPost) {
		this.likedPost = likedPost;
	}
    
}
