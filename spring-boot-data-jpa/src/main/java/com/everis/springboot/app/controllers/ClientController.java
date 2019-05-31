package com.everis.springboot.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.everis.springboot.app.models.dao.IClientDao;
import com.everis.springboot.app.models.entity.Client;

@Controller
public class ClientController {

    @Autowired
    private IClientDao daoClient;
    
    @RequestMapping(value="/listing", method = RequestMethod.GET)
    public String clientListing(Model model) {
	model.addAttribute("title", "Clients' List");
	model.addAttribute("clients", daoClient.getAll());
	return "listing";
	
    }
    
    @RequestMapping(value="/form")
    public String create(Map<String, Object> model) {
	Client client = new Client();
	model.put("client", client);
	model.put("title", "Client's Form");
	return "/form";
	
    }
    
    @RequestMapping(value="/form", method = RequestMethod.POST)
    public String save(Client client) {
	daoClient.save(client);
	return "redirect:listing";
	
    }

}
