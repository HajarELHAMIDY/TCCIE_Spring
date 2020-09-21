package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.entities.Ville;

@CrossOrigin("*")
@RepositoryRestResource
public interface VilleRepository extends JpaRepository<Ville, Long>  {
  public Ville findByName(String ville);
}
