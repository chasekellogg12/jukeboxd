//package com.example.goingSoloNew.user;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//// @RestController means that we're responding with things in a stateless manner (just presented in the browser as JSON/XML)
//	// aka, we're just responding with a User or a String (not a responsebody or something like that)
//	// the point of this is that we respond with a User or a String and the FRONTEND, not the browser, takes this response and gives a response to the browser
//
//// @Controller would instead return an HTML view
//	// so you would return a string which would be the name of an HTML view 
//@RestController
//@RequestMapping("/ddd")
//public class UserController {
//	// this is going to handle HTTP requests for users (when the base url ends in /user)
//	
//	// UserController needs a UserService to conduct business logic
//	private UserService userService;
//		
//	// because UserController needs a UserService, UserService is a dependency and therefore must be injected via @Autowired
//	// this constructor creates an instance of UserController when given a userService and a user
//	@Autowired
//	public UserController(UserService userService) {
//		this.userService = userService;
//	}
//	
//	
//	
//	// when there's a POST request for /api/users (someone wants to send us data to this url to update our Users table),
//	// create the user and add them to the table. Then, respond with the user you just created
//	// the frontend is sending a request. This request is in a JSON format, which has headers, and body, etc.
//	// @RequestBody takes the body of this request and populates the object CreateUserRequest with it
//		// this body contains name and email. So CreateUserRequest object gets populated with these things.
////	@PostMapping("/users")
////	public ResponseEntity<User> createUser(@RequestBody CreateUserRequest request) {
////		return this.userService.createUser(request.getName(), request.getEmail());
////	}
//	
//	// when there's a GET request for /api/users, respond with all of the users
//	@GetMapping("/users")
//	public ResponseEntity<List<User>> giveUsers() {
//		return this.userService.giveUsers();
//	}
//	
//	// when there's a GET request for /users/{id}, respond with just that particular user
//	@GetMapping("/email/{userEmail}")
//	public User handleUser(@PathVariable String userEmail) {
//		return this.userService.handleUsern("John", userEmail);
//	}
//}
//
//// we now have a front end that makes a post request to the backend giving the backend a name and email, which then prompts the backend to create a user
//
//// we now want it so that when you press that submit button, AFTER the post request is completed, display a new page that contains a list of all the users
//	// 
//	// when this GET 
//package com;


