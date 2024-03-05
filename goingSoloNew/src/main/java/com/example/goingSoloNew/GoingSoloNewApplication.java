package com.example.goingSoloNew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

// this was default created by initializr
// @SpringBootApplication includes 
	// @ComponentScan which scans and registers components including controllers
	// @Configuration, which tells Spring that this is the configuration class
@SpringBootApplication
@EntityScan // bc I'm using entities via JPA, we must scan for them
public class GoingSoloNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoingSoloNewApplication.class, args);
	}

}


// have a CreateController
	// RequestMapping("/create")
		// GetMapping("/user")
		// GetMapping("/post")

// we should have a table full of users
	// User - id, name, email, posts (array of strings)
	// UserController - when you go to /user, the browser displays that user's id, name, email, and posts
		// UserService will display these things
			// UserRepository is the table of users

// we should have a table full of posts
	// Post - id, text, userId, userName