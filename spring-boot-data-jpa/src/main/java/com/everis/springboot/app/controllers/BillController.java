package com.everis.springboot.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.everis.springboot.app.models.entity.*;
import com.everis.springboot.app.models.service.IClientService;

@Controller
@RequestMapping("/bill")
@SessionAttributes("bill")
public class BillController {
    
    @Autowired
    IClientService clientService; 
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @GetMapping("/look/{id}")
    public String viewDetails(@PathVariable(value="id") Long id,
	    Model model,
	    RedirectAttributes flash) {
	
	Bill bill = clientService.fetchBillByIdWithClientWithBillItemWithProduct(id);
	
	logger.info("ID: " + id.toString());
	if(bill == null) {
	    flash.addFlashAttribute("error", "Bill Does Not Exists in Database");
	    return "redirect:/listing";
	}
	
	model.addAttribute("bill", bill);
	model.addAttribute("title","Bill: ".concat(bill.getDescription()));
	return "bill/look";
	
    }

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

    
    @PostMapping("/form")
    public String save(@Valid Bill bill, 
	    BindingResult result, 
	    Model model,
	    @RequestParam(name= "item_id[]", required = false) Long[] itemId,
	    @RequestParam(name= "amount[]", required=false) Integer[] amount, 
    	    RedirectAttributes flash,
    	    SessionStatus status) {
	
	if(result.hasErrors()) {
	    model.addAttribute("title", "Create Bill");
	    return "bill/form";
	}
	
	if(itemId == null || itemId.length == 0) {
	    model.addAttribute("title", "Create Bill");
	    model.addAttribute("error", "Bills Must Not Be Line Empty");
	    return "bill/form";
	}
	
	for(int i= 0; i < itemId.length; i++) {
	    Product product = clientService.findProductById(itemId[i]);
	    BillItem line = new BillItem();
	    line.setAmount(amount[i]);
	    line.setProduct(product);
	    bill.addItem(line);
	    
	    logger.info("ID: " + itemId[i].toString() + " , amount: " + amount[i].toString());
	}
	
	clientService.saveBill(bill);
	status.setComplete();
	flash.addFlashAttribute("success", "Bill Successfully Created");
	
	return "redirect:/look/" + bill.getClientBearer().getId();
	
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value="id") Long id,
	    RedirectAttributes flash) {
	Bill bill = clientService.findBillById(id);
	
	if(bill != null) {
	    clientService.deleteBill(id);
	    flash.addFlashAttribute("success","Bill Deleted Successfully");
	    return "redirect:/look/" + bill.getClientBearer().getId();
	    
	}
	
	flash.addFlashAttribute("error", "Unable to Delete Bill");
	return "redirect:/listing";
	
    }
    
}
