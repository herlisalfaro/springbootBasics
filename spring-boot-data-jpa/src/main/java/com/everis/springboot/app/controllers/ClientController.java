package com.everis.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.everis.springboot.app.models.entity.Client;
import com.everis.springboot.app.models.service.IClientService;

@Controller
@SessionAttributes("client")
public class ClientController {

    @Autowired
    private IClientService clientService;
    
    @RequestMapping(value="/listing", method = RequestMethod.GET)
    public String clientListing(Model model) {
	model.addAttribute("title", "CLIENTS' LIST");
	model.addAttribute("clients", clientService.findAll());
	return "listing";
	
    }
    
    @RequestMapping(value="/form")
    public String create(Map<String, Object> model) {
	Client client = new Client();
	model.put("client", client);
	model.put("title", "CLIENT'S FORM");
	return "form";
	
    }
    
    @RequestMapping(value ="/form/{id}")
    public String edit(@PathVariable(value= "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
	Client client = null;
	
	if(id > 0) {
	    client = clientService.findOne(id);
	    if(client == null) {
		flash.addFlashAttribute("error", "Client's Id Does Not Exist");
		return "redirect:/listing";
	    }
	} else {
	    flash.addFlashAttribute("error", "Client's Id cannot be 0");
	    return "redirect:/listing";
	}
	model.put("client", client);
	model.put("title", "Client's Update");
	return "form";
	
    }
    
    @RequestMapping(value="/form", method = RequestMethod.POST)
    public String save(@Valid Client client, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
	if(result.hasErrors()) {
	    model.addAttribute("title", "Client's Form");
	    return "form";
	}
	String flashMessage = (client.getId() != null)? "Client Created Successfully" : "Client Updated Successfully";
	clientService.save(client);
	flash.addFlashAttribute("success", flashMessage);
	status.setComplete();
	return "redirect:/listing";
	
    }
    
    @RequestMapping(value="/delete/{id}")
    public String delete(@PathVariable(value= "id") Long id, RedirectAttributes flash) {
	if(id > 0) {
	    clientService.delete(id);
	    flash.addFlashAttribute("success", "Client Deleted Successfully");
	}
	    return "redirect:/listing";
    }
    
    
    
    

}
