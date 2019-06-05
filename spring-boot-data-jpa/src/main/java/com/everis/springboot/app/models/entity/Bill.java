package com.everis.springboot.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "bills")
public class Bill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String observations;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client clientBearer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id")
    private List<BillItem> items;

    public Bill() {
	items = new ArrayList<BillItem>();
    }

    @PrePersist
    public void prePersist() {
	createdAt = new Date();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getObservations() {
	return observations;
    }

    public void setObservations(String observations) {
	this.observations = observations;
    }

    public Date getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
    }

    public Client getClientBearer() {
	return clientBearer;
    }

    public void setClientBearer(Client clientBearer) {
	this.clientBearer = clientBearer;
    }

    public List<BillItem> getItems() {
	return items;
    }

    public void setItems(List<BillItem> items) {
	this.items = items;
    }

    public void addItem(BillItem item) {
	this.items.add(item);
    }

    public Double getTotal() {
	Double total = 0.0;

	int size = items.size();
	for (int i = 0; i < size; i++) {
	    total += items.get(i).totalAmount();
	}

	return total;
    }

    private static final long serialVersionUID = 1L;

}
