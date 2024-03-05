//package com.example.goingSoloNew.user;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//	// in order to do stuff, UserService needs access to the database (repository).
//	// so, it contains a UserRepository, which is a dependency, and therefore must be autowired in during the constructor of UserService
//	
//	private UserRepository userRepository;
//	
//	@Autowired
//	public UserService(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	} // so now, when you create an instance of UserService, you must give it a repository to work with
//	
//	public String showCreateUserPage() {
//		return "";
//	}
//	
//	// creates a new user, adds them to the repository, then reuturns a response entity
//	public void createUser(String name, String email, String username, String password) {
//		User newUser = new User(name, email, username, password);
//		userRepository.save(newUser);
//	}
//	
//	
//	// simply returns a list of all the users
//	public ResponseEntity<List<User>> giveUsers() {
//		return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
//	}
//	
//	// handle being given the name and email of a user
//	public User handleUsern(String name, String email) {
//		// if this email is already in the repository, just display its info. Otherwise, create it then display info
//		if (!userRepository.existsByEmail(email)) {
//			User newUser = new User();
//			newUser.setName(name);
//			newUser.setEmail(email);
//			userRepository.save(newUser);
//		}
//		
//		return userRepository.findByEmail(email);
//	}
//	
//	// we want to be able to Delete a User
//	
//	// we want to be able to change things about a specific User
//	
//}
//package com;


