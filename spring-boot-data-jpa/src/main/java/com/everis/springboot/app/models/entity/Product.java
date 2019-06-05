package com.everis.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name= "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    private String name;
    
    
    private Double price;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;
    
    @PrePersist
    public void prePersist() {
	this.createdAt = new Date();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Double getPrice() {
	return price;
    }

    public void setPrice(Double price) {
	this.price = price;
    }

    public Date getCreated_At() {
	return createdAt;
    }

    public void setCreated_At(Date created_At) {
	this.createdAt = created_At;
    }

    private static final long serialVersionUID = 1L;

}
