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
public class Rang implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRang;
	private String nameRang;
	private String descRang;
	@OneToMany(mappedBy="rang")
	private List<Product> products;
	public Rang(Long idRang, String nameRang, String descRang) {
		super();
		this.idRang = idRang;
		this.nameRang = nameRang;
		this.descRang = descRang;
		this.products = products = new ArrayList();
	}
	public Rang() {
		this.products = products = new ArrayList();
	}
	public Long getIdRang() {
		return idRang;
	}
	public void setIdRang(Long idRang) {
		this.idRang = idRang;
	}
	public String getNameRang() {
		return nameRang;
	}
	public void setNameRang(String nameRang) {
		this.nameRang = nameRang;
	}
	public String getDescRang() {
		return descRang;
	}
	public void setDescRang(String descRang) {
		this.descRang = descRang;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	

}
