package com.everis.springboot.di.app.models.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class Billing {

    @Value("${billing.description}")
    private String description;
    @Autowired
    private Client client;
    @Autowired
    private List<BillingItem> items;

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Client getClient() {
	return client;
    }

    public void setClient(Client client) {
	this.client = client;
    }

    public List<BillingItem> getItems() {
	return items;
    }

    public void setItems(List<BillingItem> items) {
	this.items = items;
    }

}
