package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Reseller implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReseller;
	private String nameReseller;
	private String addresse;
	private String tel;
	private String image; 
	public Reseller() {
		
	}
	public Reseller(Long idReseller, String nameReseller, String addresse) {
		super();
		this.idReseller = idReseller;
		this.nameReseller = nameReseller;
		this.addresse = addresse;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getIdReseller() {
		return idReseller;
	}
	
	public void setIdReseller(Long idReseller) {
		this.idReseller = idReseller;
	}
	public String getNameReseller() {
		return nameReseller;
	}
	public void setNameReseller(String nameReseller) {
		this.nameReseller = nameReseller;
	}
	public String getAddresse() {
		return addresse;
	}
	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}
	

}
