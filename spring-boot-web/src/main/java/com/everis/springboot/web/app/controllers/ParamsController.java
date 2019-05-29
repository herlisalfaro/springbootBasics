package com.everis.springboot.web.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/params")
public class ParamsController {

    @GetMapping("/")
    public String index() {
	return "params/index";
    }
    
    @GetMapping("/string")
    public String param(@RequestParam(name="text",required=false, defaultValue ="oops") String text, Model model) {
	model.addAttribute("result", "Sent Text: " + text);
	return "params/check";
    }
    
    @GetMapping("/mixed-params")
    public String param(@RequestParam String salute, @RequestParam Integer number, Model model) {
	model.addAttribute("result", "Salute is: '" + salute + "' and number is: '" + number + "'");
	return "params/check";
    }
    
    @GetMapping("/mixed-params-request")
    public String param(HttpServletRequest request, Model model) {
	String salute = request.getParameter("salute");
	Integer number = null;
	try {
	number = Integer.parseInt(request.getParameter("number"));
	} catch(NumberFormatException e) {
	    number = 0;
	}
	model.addAttribute("result", "Salute is: '" + salute + "' and number is: '" + number + "'");
	return "params/check";
    }


}
