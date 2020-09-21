package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.entities.OrderC;
import com.example.demo.entities.OrderItems;
@CrossOrigin("*")
@RepositoryRestResource
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long>{
	List<OrderItems> findByOrder(OrderC orderc );	

}
