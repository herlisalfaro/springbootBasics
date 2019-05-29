package com.everis.springboot.web.app.controllers;


import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.everis.springboot.web.app.models.User;

@Controller
@RequestMapping("/app")
public class IndexController {

    @GetMapping({ "/index", "/", "", "/home" })
    public String index(Model model) {
	model.addAttribute("title", "Hello, Spring Framework!");
	return "index";
    }

    @RequestMapping("/profile")
    public String profile(Model model) {

	User user = new User();
	user.setName("Charlie");
	user.setSurname("NoLife");
	user.setEmail("nolife@email.com");

	model.addAttribute("user", user);
	model.addAttribute("title", "User's Profile: ".concat(user.getName()));
	return "profile";
    }

    @RequestMapping("/list")
    public String list(Model model) {
	
	model.addAttribute("title", "User's List");
	return "list";
    }
    
    @ModelAttribute("users")
    public List<User> userList() {
	List<User> users = Arrays.asList(new User("Charlie", "NoLife", "nolife@email.com"),
		new User("Frank", "BetterForm", "better@email.com"),
		new User("Leslie", "Truth", "truth@email.com"),
		new User("Mike", "Iron", "iron@email.com"));
	
	return users;
    }
}
