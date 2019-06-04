package com.everis.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.everis.springboot.app.models.dao.IDaoClient;
import com.everis.springboot.app.models.entity.Client;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IDaoClient daoClient;

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

}
