package com.everis.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.everis.springboot.app.models.entity.Client;

@Repository
public class ClientDaoImpl implements IClientDao {

    @PersistenceContext
    private EntityManager em;

    

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Client> getAll() {

	List<Client> clientList = em.createQuery("from Client").getResultList();
	return clientList;
    }



    @Override
    @Transactional
    public void save(Client client) {
	em.persist(client);
	
    }

}
