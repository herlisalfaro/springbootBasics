package com.everis.springboot.app.models.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.everis.springboot.app.models.entity.Bill;


public interface IDaoBill extends CrudRepository<Bill, Long>{

    @Query("select b from Bill b JOIN FETCH b.clientBearer c JOIN FETCH b.items l JOIN FETCH l.product WHERE b.id =?1")
    public Bill fetchByIdWithClientWithBillItemWithProduct(Long id);
}
