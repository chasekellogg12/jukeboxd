package com.example.goingSoloNew.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// User = the entity that we are storing the table (THIS IS T)
// Long = the type of what we're using to uniquely identify this entity (THIS IS ID)
public interface UserRepository extends JpaRepository<MyUser, String> {
	// this interface has a bunch of methods it inhertis from JpaRepository which handle CRUD opertations
		// like save(User entity)
		// findById(ID id)
    	// findAll() 
    	// deleteById(ID id)
	
	// interface = rules that a class must follow if that class implements the interface
		// GamingConsole had up() down() left() right(). MarioGame, PacmanGame, etc. all implemented GamingConsole
		// and therefore had to do have the methods up(), down(), left(), right()
		// the GameRunner class would take in a GamingConsole and run all four of its methods
		// then in the main method, you would just create an instance of the MarioGame,
		// then create an instance of GameRunner with this MarioGame as it's parameter and everything would work
	
    @Query("SELECT u FROM MyUser u WHERE u.username IN :usernames")
    List<MyUser> findByUsernames(@Param("usernames") List<String> usernames);
	
    @Query("SELECT u.username FROM MyUser u")
    List<String> findAllUsernames();
    
    @Modifying
    @Query("update MyUser u set u.followers = :followers where u.username = :username")
    void setFollowersList(@Param("followers") List<MyUser> followers, @Param("username") String username);
    
    @Modifying
    @Query("update MyUser u set u.following = :following where u.username = :username")
    void setFollowingList(@Param("following") List<MyUser> following, @Param("username") String username);
    
	boolean existsByEmail(String email);
	MyUser findByEmail(String email);
}
