package com.everis.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "bills_items")
public class BillItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Product product;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Integer getAmount() {
	return amount;
    }

    public void setAmount(Integer amount) {
	this.amount = amount;
    }
    
    public Double totalAmount() {
	return amount.doubleValue() * product.getPrice();
    }

    private static final long serialVersionUID = 1L;

}
