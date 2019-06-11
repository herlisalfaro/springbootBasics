package com.everis.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.everis.springboot.app.models.dao.IDaoClient;
import com.everis.springboot.app.models.dao.IDaoProduct;
import com.everis.springboot.app.models.entity.Client;
import com.everis.springboot.app.models.entity.Product;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IDaoClient daoClient;
    
    @Autowired
    private IDaoProduct daoProduct;

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
	return (List<Client>) daoClient.findAll();
    }

    @Override
    @Transactional
    public void save(Client client) {
	daoClient.save(client);

    }

    @Override
    @Transactional(readOnly = true)
    public Client findOne(Long id) {

	return daoClient.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
	daoClient.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<Client> findAll(Pageable pageable) {
	return daoClient.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByName(String term) {
	return daoProduct.findByNameLikeIgnoreCase("%"+term+"%");
    }

}
