package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Image implements Serializable{
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idImage ;
	private String nameImage;
	@ManyToOne
	private Product product;
	public Image() {
		
	}
	public Image(Long idImage, String nameImage) {

		this.idImage = idImage;
		this.nameImage = nameImage;
	}
	public Long getIdImage() {
		return idImage;
	}
	public void setIdImage(Long idImage) {
		this.idImage = idImage;
	}
	public String getNameImage() {
		return nameImage;
	}
	public void setNameImage(String nameImage) {
		this.nameImage = nameImage;
	}
	
	
	
	

}
