package com.example.goingSoloNew.myLike;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.goingSoloNew.user.MyUser;

public interface MyLikeRepository extends JpaRepository<MyLike, Long> {
	
    @Query("SELECT ml FROM MyLike ml WHERE ml.liker.username = :user AND ml.likedPost.postId = :post")
    MyLike findByUserAndPost(@Param("user") String username, @Param("post") Long postId);
}
