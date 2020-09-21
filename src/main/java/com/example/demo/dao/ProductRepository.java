package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.entities.Product;
@CrossOrigin("*")
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@RestResource(path="/promoProduct")
	public List<Product> findByPromotionTrue();
	@Query("select u from Product u where u.category.idCategory like :x1 and  u.rang.idRang like :x2")  
	public List<Product> filtre(@Param("x1") Long idCat,@Param("x2") Long idRang );

}
