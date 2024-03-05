//package com.example.goingSoloNew.hello;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//// This is a controller, which is a component that handles incoming requests from web browsers
//	// they interpret the request, extract data like headers/parameters, and determine how to handle the request
//
//// @RestController tells spring that this class will handle HTTP requests and that its methods will return the response body
//@RestController
//
//// @RequestMapping tells spring that all methods in this class will handle requests with a base URL of /api/
////@RequestMapping("/yo")
//public class HelloController {
//	
//	// so basically, the HelloController class contains a HelloService object called helloService
//	private final HelloService helloService;
//	
//	// when you construct HelloController in this constructer, you inject a HelloService into it via @Autowired
//	// because helloService is a dependency of HelloController
//	@Autowired
//	public HelloController(HelloService helloService) {
//		this.helloService = helloService;
//	}
//
//    // @GetMapping is a specialized version of @RequestMethod that maps HTTP GET requests
//	// in this case, when a GET request occurs for /api/hello endpoint, run this method 
//	@GetMapping("/hello")
//    public String sayHello() {
//        return helloService.getHelloMessage();
//    }
//	
//	// if path ends in {variable}, @PathVariable takes this variable and assigns it to Name
//	// if path doesnt, you can use @RequestParam instead. This takes whatever is after '?' in URL and uses it as the parameter
//		// ex: /greet?Name=Chase
//	@GetMapping("/greet")
//	public String sayGreeting(@RequestParam String Name) {
//		return helloService.getGreeting(Name);
//	}
//}
//
