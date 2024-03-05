package com.example.goingSoloNew.hello;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class MyUser extends User implements MyUserDetails{
	private static final long serialVersionUID = 1L; // You can choose any long value
	
	private String name;
	private String email;
	
	public MyUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
            String email, String name) {
		super(username, password, authorities);
		this.name = name;
		this.email = email;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getEmail() {
		return email;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
