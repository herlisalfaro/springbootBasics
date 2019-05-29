package com.everis.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/variables")
public class VariablePathController {

    @GetMapping("/")
    public String index(Model model) {
	model.addAttribute("title", "Sending Various Parameters from Route (@PathVariable)");
	return "variables/index";
    }

    @GetMapping("/string/{text}")
    public String variables(@PathVariable String text, Model model) {
	model.addAttribute("title", "Sending Various Parameters from Route (@PathVariable)");
	model.addAttribute("result", "Text Received from Route is: " + text);
	return "variables/check";
    }

    @GetMapping("/string/{text}/{number}")
    public String variables(@PathVariable String text, @PathVariable Integer number, Model model) {
	model.addAttribute("title", "Sending Various Parameters from Route (@PathVariable)");
	model.addAttribute("result",
		"Text Received from Route is " + text + " and Number Received from Route is " + number);
	return "variables/check";
    }

}
