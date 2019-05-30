package com.everis.springboot.di.app.models.domain;

public class BillingItem {

    private Product product; 
    private Integer amount;
    
    
    
    public BillingItem(Product product, Integer amount) {
	this.product = product;
	this.amount = amount;
    }
    
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    } 
    
    public Integer TotalCalculator() {
	return amount * product.getPrice();
    }
    
}
