package com.everis.springboot.app.models.dao;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.everis.springboot.app.models.entity.Client;

public interface IDaoClient extends PagingAndSortingRepository<Client, Long> {}
