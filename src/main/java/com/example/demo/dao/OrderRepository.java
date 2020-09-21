package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.entities.OrderC;

@CrossOrigin("*")
@RepositoryRestResource
public interface OrderRepository extends JpaRepository<OrderC, Long>{

}
