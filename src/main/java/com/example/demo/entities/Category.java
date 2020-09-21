package com.example.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategory;
	private String nameCategory;
	private String desCategory;
	@OneToMany (mappedBy="category")
	private List<Product> products;
	public Category() {
		
	}
	public Category(Long idCategory, String nameCategory, String desCategory) {
		super();
		this.idCategory = idCategory;
		this.nameCategory = nameCategory;
		this.desCategory = desCategory;
		this.products = new ArrayList<Product>();
	}
	public Long getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}
	public String getNameCategory() {
		return nameCategory;
	}
	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
	public String getDesCategory() {
		return desCategory;
	}
	public void setDesCategory(String desCategory) {
		this.desCategory = desCategory;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
	
	
	

}
