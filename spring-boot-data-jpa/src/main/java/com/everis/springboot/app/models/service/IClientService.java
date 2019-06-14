package com.everis.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.*;

import com.everis.springboot.app.models.entity.Bill;
import com.everis.springboot.app.models.entity.Client;
import com.everis.springboot.app.models.entity.Product;

public interface IClientService {

    public List<Client> findAll();
    
    public Page<Client> findAll(Pageable pageable);

    public void save(Client client);

    public Client findOne(Long id);
    
    public Client fetchByIdWithBills(Long id);

    public void delete(Long id);
    
    public List<Product> findByName(String term);
    
    public void saveBill(Bill bill);
    
    public Product findProductById(Long id);
    
    public Bill findBillById(Long id);
    
    public void deleteBill(Long id);
    
    public Bill fetchBillByIdWithClientWithBillItemWithProduct(Long id); 
}
