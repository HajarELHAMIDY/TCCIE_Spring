package com.example.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


@Entity
public class Product implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduct;
	private String nameProduct;
	private String descProduct;
	private float price;
	private Boolean promotion;
	private Boolean opt; 
	private int stock;
	@Transient
	int quantity=1;
	@ManyToOne 
	private Category category;

	@ManyToOne
	private Rang rang ;
	private String imageP;
	@OneToMany (mappedBy = "product")
	private List<Image> images ;
	
	public Boolean getOpt() {
		return opt;
	}
	public void setOpt(Boolean opt) {
		this.opt = opt;
	}
	public Product() {
		this.images = new ArrayList();
		this.imageP="/image.png";
		
	}
	public void addImage(Image image) {
		this.images.add(image);
	}


	public Product(Long idProduct, String nameProduct, String descProduct, float price, Boolean publicationStatus,
			int stock, Category category, Rang rang, String image) {
		
		this.idProduct = idProduct;
		this.imageP = image;
		this.nameProduct = nameProduct;
		this.descProduct = descProduct;
		this.price = price;
		this.promotion = publicationStatus;
		this.stock = stock;
		this.category = category;
		this.rang = rang;
		this.images = new ArrayList();
		this.imageP="/image.png";
	}


	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getImageP() {
		return imageP;
	}
	public void setImageP(String imageP) {
		this.imageP = imageP;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getDescProduct() {
		return descProduct;
	}

	public void setDescProduct(String descProduct) {
		this.descProduct = descProduct;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Boolean getPromotion() {
		return this.promotion;
	}

	public void setPromotion(Boolean publicationStatus) {
		this.promotion = publicationStatus;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Category getCatgory() {
		return category;
	}

	public void setCatgory(Category catgory) {
		this.category = catgory;
	}

	public Rang getRang() {
		return rang;
	}

	public void setRang(Rang rang) {
		this.rang = rang;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	
	
	

}
