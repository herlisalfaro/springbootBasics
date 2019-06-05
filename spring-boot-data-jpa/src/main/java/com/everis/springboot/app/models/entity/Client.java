package com.everis.springboot.app.models.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "clients")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    @NotEmpty
    @Size(min=4, max=12)
    private String name;

    @Column(name = "SURNAME")
    @NotEmpty
    @Size(min=4, max=12)
    private String surname;

    @Column(name = "EMAIL")
    @NotEmpty
    @Email
    private String email;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern= "yyyy-MM-dd")
    @NotNull
    private Date createdAt;
    
    @OneToMany(mappedBy="clientBearer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Bill> bills;
    
    
    
    public Client() {
	bills = new ArrayList<Bill>();
    }


    private String picture;

    
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

    public String getSurname() {
	return surname;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Date getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

   
    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
    
    public void addBill(Bill bill) {
	this.bills.add(bill);
    }


    private static final long serialVersionUID = 1L;

}
