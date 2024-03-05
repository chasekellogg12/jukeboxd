package com.example.goingSoloNew.user;

// this object will contain data sent frontend (name and email)
// when a POST request is received by our controller, a method will be called. This object will be passed in as a parameter
// we'll be using @ResponseBody, which will populate this object with whatever the response body contains (the body of what the frontend sends)
	// bc the body of what the frontend sends contains just two things: name and email, that's what this object will contain
	// to allow ResponseBody to populate this, we must have getters and setters and a default constructer

public class CreateUserRequest {
	
	// unsafe to have public getters and setters for password?
	private String name;
	private String email;
	private String username;
	private String password;
	
	public CreateUserRequest() {
		
	}
	
	public CreateUserRequest(String name, String email, String username, String password) {
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	// needs getters and setters 
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
