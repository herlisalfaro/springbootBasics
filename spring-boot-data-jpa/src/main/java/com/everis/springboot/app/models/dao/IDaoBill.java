package com.everis.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.everis.springboot.app.models.entity.Bill;

public interface IDaoBill extends CrudRepository<Bill, Long>{

}
