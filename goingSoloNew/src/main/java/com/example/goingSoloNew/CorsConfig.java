package com.example.goingSoloNew;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // just means that this class has bean delcarations in it
public class CorsConfig implements WebMvcConfigurer {
	@Override // this means that if WebMvcConfigurer has a method called "addCorsMapping()", use the method in this class isntead of that one
	public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Adjust the mapping based on your backend API
//        	.allowedOrigins("http://localhost:3000")  // The origin of your React app
        	.allowedOrigins("*")
        	.allowedMethods("GET", "POST", "PUT", "DELETE");
	} // basically saying: from localhost:3000 can request GET, POST, PUT or DELETE from /
}
