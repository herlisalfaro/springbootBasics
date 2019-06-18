package com.everis.springboot.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.everis.springboot.app.models.entity.Client;
import com.everis.springboot.app.models.service.IClientService;
import com.everis.springboot.app.models.service.IUploadFileService;
import com.everis.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("client")
public class ClientController {

    protected final Log logger = LogFactory.getLog(this.getClass());
    
    @Autowired
    private IClientService clientService;
    @Autowired
    private IUploadFileService uploadFileService;

    @Secured(value = { "ROLE_USER" }) 
    @GetMapping(value = "/uploads/{filename:.+}")
    public ResponseEntity<Resource> showPicture(@PathVariable String filename) {
	Resource resource = null;
	try {
	    resource = uploadFileService.loadPicture(filename);
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	}
	return ResponseEntity.ok()
		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
		.body(resource);
    }

    
    @Secured(value = { "ROLE_USER" })
    @GetMapping("/look/{id}")
    public String look(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
	Client client = clientService.fetchByIdWithBills(id);
	if (client == null) {
	    flash.addFlashAttribute("error", "Client Does Not Exist");
	    return "redirect:/listing";
	}
	model.put("client", client);
	model.put("title", "Client Details: " + client.getName());
	return "look";

    }

    @RequestMapping(value = {"/listing","/"}, method = RequestMethod.GET)
    public String clientListing(@RequestParam(name = "page", defaultValue = "0") int page, Model model, 
	    Authentication authentication,
	    HttpServletRequest request) {
	
	if(authentication != null) {
	    logger.info("Hello auth User, your username is: " + authentication.getName());
	}
	
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if(auth != null) {
	    logger.info("Static Mode: Hello auth User, your username is: " + auth.getName());
	}
	
	if(hasRole("ROLE_ADMIN")) {
	    logger.info("Hello, " + auth.getName() + ", You've Got Access!");
	}else {
	    logger.info("Sorry, " + auth.getName() + ", You Do Not Have Access");
	}
	
	/*Short Ways to validate Roles*/
	SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request,"ROLE_");
	if(securityContext.isUserInRole("ADMIN")) {
	    logger.info("Using SecurityContextHolderAwareRequestWrapper: Hello, " + auth.getName() + ", You've Got Access!");
	}else {
	    logger.info("Using SecurityContextHolderAwareRequestWrapper: Sorry, " + auth.getName() + ", You Do Not Have Access");
	}
	
	if(request.isUserInRole("ROLE_ADMIN")) {
	    logger.info("Using HttpServletRequest: Hello, " + auth.getName() + ", You've Got Access!");
	}else {
	    logger.info("Using HttpServletRequest: Sorry, " + auth.getName() + ", You Do Not Have Access");
	}
	
	Pageable pageRequest = PageRequest.of(page, 5);
	Page<Client> clients = clientService.findAll(pageRequest);
	PageRender<Client> pageRender = new PageRender<>("/listing", clients);
	model.addAttribute("title", "CLIENTS' LIST");
	model.addAttribute("clients", clients);
	model.addAttribute("page", pageRender);
	return "listing";

    }

    @Secured(value = { "ROLE_ADMIN" }) 
    @RequestMapping(value = "/form")
    public String create(Map<String, Object> model) {
	Client client = new Client();
	model.put("client", client);
	model.put("title", "CLIENT'S FORM");
	return "form";

    }

    @Secured(value = { "ROLE_ADMIN" })
    @RequestMapping(value = "/form/{id}")
    public String edit(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
	Client client = null;

	if (id > 0) {
	    client = clientService.findOne(id);
	    if (client == null) {
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
    
    @Secured(value = { "ROLE_ADMIN" })
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String save(@Valid Client client, BindingResult result, Model model,
	    @RequestParam("file") MultipartFile picture, RedirectAttributes flash, SessionStatus status) {
	if (result.hasErrors()) {
	    model.addAttribute("title", "Client's Form");
	    return "form";
	}

	if (!picture.isEmpty()) {

	    if (client.getId() != null && client.getId() > 0 && client.getPicture() != null
		    && client.getPicture().length() > 0) {

		uploadFileService.deletePicture(client.getPicture());

	    }
	    String uniqueFileName = null;
	    try {
		uniqueFileName = uploadFileService.getNewPicture(picture);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    flash.addFlashAttribute("info", "Your image " + uniqueFileName + " Has Been Successfully Uploaded");
	    client.setPicture(uniqueFileName);
	}

	String flashMessage = (client.getId() != null) ? "Client Created Successfully" : "Client Updated Successfully";
	clientService.save(client);
	flash.addFlashAttribute("success", flashMessage);
	status.setComplete();
	return "redirect:/listing";

    }

    @Secured(value = { "ROLE_ADMIN" })
    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
	if (id > 0) {
	    Client client = clientService.findOne(id);
	    clientService.delete(id);
	    flash.addFlashAttribute("success", "Client " + client.getId() + " Deleted Successfully");
	    if (uploadFileService.deletePicture(client.getPicture())) {
		flash.addFlashAttribute("info", "Client's picture " + client.getPicture() + " Deleted Successfully");
	    }
	}
	return "redirect:/listing";
    }
    
    
    private boolean hasRole(String role) {
	
	SecurityContext context = SecurityContextHolder.getContext();
	
	if(context == null) {
	    return false;
	}
	
	Authentication auth = context.getAuthentication();
	
	if(auth == null) {
	    return false;
	}
	
	Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

	return authorities.contains(new SimpleGrantedAuthority(role));
	
	/*
	 * Manual way to validate roles:
	 * 
	 * for(GrantedAuthority authority: authorities) {
	
	    if(role.equals(authority.getAuthority())) {
		logger.info("Hello, " + auth.getName() + ", Your Role Is: " + authority.getAuthority());
		return true;
	    }
	}
	return false;
	 */
    }

}
