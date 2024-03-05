package com.example.goingSoloNew.hello;
import org.springframework.stereotype.Service;

// This basically indicates to spring that this is a component (and therefore make a bean for it
// and in particular, it is a service, meaning it contains the business logic)
@Service
public class HelloService {
	public String getHelloMessage() {
		return "Hello, world! (using service)";
	}
	
	public String getGreeting(String name) {
		return "Hello, " + name + "!";
	}
}
