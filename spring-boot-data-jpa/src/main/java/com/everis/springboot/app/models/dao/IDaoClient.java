package com.everis.springboot.app.models.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.everis.springboot.app.models.entity.Client;

public interface IDaoClient extends PagingAndSortingRepository<Client, Long> {
    
    @Query("select c from Client c left join fetch c.bills b where c.id=?1")
    public Client fetchByIdWithBills(Long id);
}
