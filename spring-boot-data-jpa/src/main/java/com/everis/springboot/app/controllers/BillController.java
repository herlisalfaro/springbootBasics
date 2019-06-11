package com.everis.springboot.app.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.everis.springboot.app.models.entity.Bill;
import com.everis.springboot.app.models.entity.Client;
import com.everis.springboot.app.models.entity.Product;
import com.everis.springboot.app.models.service.IClientService;

@Controller
@RequestMapping("/bill")
@SessionAttributes("bill")
public class BillController {
    
    @Autowired
    IClientService clientService; 

    @GetMapping("/form/{clientId}")
    public String createBill(@PathVariable(value="clientId") Long clientId, 
	    Map<String, Object> model, 
	    RedirectAttributes flash) {
	
	Client client = clientService.findOne(clientId);
	if(client == null) {
	    flash.addFlashAttribute("error", "Client Id Do Not Exist in Database");
	    return "redirect:/listing"; 
	}
	
	Bill bill = new Bill();
	bill.setClientBearer(client);
	
	model.put("bill", bill);
	model.put("title", "Create New Bill");
	
	return "bill/form";
    }
    
    @GetMapping(value ="/product-load/{term}", produces= {"application/json"})
    public @ResponseBody List<Product> productsLoad(@PathVariable String term) {
	return clientService.findByName(term);
    }
    
}
