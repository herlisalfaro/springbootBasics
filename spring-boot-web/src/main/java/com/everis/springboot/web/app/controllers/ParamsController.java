package com.everis.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/params")
public class ParamsController {

    @GetMapping("/index")
    public String index() {
	return "param/index";
    }
    
    @GetMapping("/string")
    public String param(@RequestParam(name="text",required=false, defaultValue ="oops") String text, Model model) {
	model.addAttribute("result", "Sent Text: " + text);
	return "params/check";
    }
}
