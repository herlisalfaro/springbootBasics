package com.everis.springboot.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.*;
import org.springframework.http.*;
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

    @Autowired
    private IClientService clientService;
    @Autowired
    private IUploadFileService uploadFileService;

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

    @GetMapping("/look/{id}")
    public String look(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
	Client client = clientService.findOne(id);
	if (client == null) {
	    flash.addFlashAttribute("error", "Client Does Not Exist");
	    return "redirect:/listing";
	}
	model.put("client", client);
	model.put("title", "Client Details: " + client.getName());
	return "look";

    }

    @RequestMapping(value = "/listing", method = RequestMethod.GET)
    public String clientListing(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
	Pageable pageRequest = PageRequest.of(page, 5);
	Page<Client> clients = clientService.findAll(pageRequest);
	PageRender<Client> pageRender = new PageRender<>("/listing", clients);
	model.addAttribute("title", "CLIENTS' LIST");
	model.addAttribute("clients", clients);
	model.addAttribute("page", pageRender);
	return "listing";

    }

    @RequestMapping(value = "/form")
    public String create(Map<String, Object> model) {
	Client client = new Client();
	model.put("client", client);
	model.put("title", "CLIENT'S FORM");
	return "form";

    }

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

}
