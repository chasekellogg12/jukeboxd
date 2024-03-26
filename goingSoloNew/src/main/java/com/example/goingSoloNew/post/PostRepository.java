package com.example.goingSoloNew.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.goingSoloNew.user.MyUser;

public interface PostRepository extends JpaRepository<Post, Long> {
    
	@Query("SELECT p FROM Post p WHERE p.author IN :users")
    List<Post> findByAuthorsIn(@Param("users") List<MyUser> users);
	
    @Query("SELECT AVG(p.rating) FROM Post p WHERE p.postSubject.trackId = :trackId")
    Float findAverageRating(@Param("trackId") String trackId);
}
