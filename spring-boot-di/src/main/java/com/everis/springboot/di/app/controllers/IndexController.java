package com.everis.springboot.di.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.everis.springboot.di.app.models.service.IService;


@Controller
public class IndexController {
    
    
    @Autowired
    @Qualifier("SimpleService")
    private IService service;
    
    @GetMapping({"/", "/index", ""})
    public String index(Model model) {
	model.addAttribute("service", service.operation());
	return "index";
    }

    
}
