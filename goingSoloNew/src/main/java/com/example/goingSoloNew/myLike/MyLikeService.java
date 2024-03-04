package com.example.goingSoloNew.myLike;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goingSoloNew.post.Post;
import com.example.goingSoloNew.post.PostRepository;
import com.example.goingSoloNew.user.MyUser;
import com.example.goingSoloNew.user.NewActivity;
import com.example.goingSoloNew.user.UserRepository;

@Service
public class MyLikeService {
	@Autowired
	private MyLikeRepository myLikeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	public MyLikeService() {
		
	}
	
	public void handleCreateLike(String username, Long postId) {
		MyUser user = userRepository.findById(username).orElse(null);
		Post post = postRepository.findById(postId).orElse(null);
		
		if (user != null && post != null) {
		    MyLike like = new MyLike();
		    like.setLiker(user);
		    like.setLikedPost(post);
		    myLikeRepository.save(like);
		    

		    user.getLikes().add(like);
		    post.getLikes().add(like);
		    postRepository.save(post);
		    
			Calendar c = Calendar.getInstance();
			List<NewActivity> newRecentActivity = user.getRecentActivity();
			newRecentActivity.add(new NewActivity("@" + user.getUsername(), " liked a review by ", "@" + post.getAuthor().getUsername(), c.getTime()));
			user.setRecentActivity(newRecentActivity);
			userRepository.save(user);
		}
	}
	
}
