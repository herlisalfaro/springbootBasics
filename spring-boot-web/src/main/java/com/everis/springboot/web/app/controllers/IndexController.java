package com.everis.springboot.web.app.controllers;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.everis.springboot.web.app.models.User;

@Controller
@RequestMapping("/app")
public class IndexController {
    
    @Value("${text.indexcontroller.index.title}")
    private String indexText;
    @Value("${text.indexcontroller.profile.title}")
    private String profileText;
    @Value("${text.indexcontroller.list.title}")
    private String listText;
    
    @GetMapping({ "/index", "/", "", "/home" })
    public String index(Model model) {
	model.addAttribute("title", indexText);
	return "index";
    }

    @RequestMapping("/profile")
    public String profile(Model model) {

	User user = new User();
	user.setName("Charlie");
	user.setSurname("NoLife");
	user.setEmail("nolife@email.com");

	model.addAttribute("user", user);
	model.addAttribute("title", profileText.concat(user.getName()));
	return "profile";
    }

    @RequestMapping("/list")
    public String list(Model model) {
	
	model.addAttribute("title", listText);
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
