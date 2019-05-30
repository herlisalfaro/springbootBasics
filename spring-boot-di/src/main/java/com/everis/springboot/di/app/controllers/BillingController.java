package com.everis.springboot.di.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.everis.springboot.di.app.models.domain.Billing;

@Controller
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private Billing billOne;
    
    @GetMapping("/bill")
    public String checkBilling(Model model) {
	model.addAttribute("bill", billOne);
	model.addAttribute("title", "Client Billing Check");
	return "billing/bill";
    }
}
