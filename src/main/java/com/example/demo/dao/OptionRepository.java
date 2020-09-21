package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.entities.OptionP;
@CrossOrigin("*")
@RepositoryRestResource
public interface OptionRepository extends JpaRepository<OptionP, Long> {
	OptionP findByName(String name); 

}
