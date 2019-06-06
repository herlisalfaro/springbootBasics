package com.everis.springboot.app.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bill")
public class BillController {

    @GetMapping("/form/{clientId}")
    public String createBill(@PathVariable(value="clientId") Long clientId, Map<String, Object> model) {
	return "bill/form";
    }
    
}
