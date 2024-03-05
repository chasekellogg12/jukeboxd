package com.example.goingSoloNew.hello;

import org.springframework.security.core.userdetails.UserDetails;

public interface MyUserDetails extends UserDetails {
	String getName();
	String getEmail();
}
