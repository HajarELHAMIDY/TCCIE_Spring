package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class OrderItems implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOrderItem;
	@ManyToOne
	private Product product;
	private float price;
	private int quantity;
	private String optionP ; 
	@ManyToOne
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private OrderC order;

	public OrderItems() {

	}

	public OrderItems(Long idOrderItem, OrderC order, Product product, float price, int quantity) {
		super();
		this.idOrderItem = idOrderItem;
		this.order = order;
		this.product = product;
		this.price = price;
		this.quantity = quantity;
	}

	public String getOptionP() {
		return optionP;
	}

	public void setOptionP(String optionP) {
		this.optionP = optionP;
	}

	public Long getIdOrderItem() {
		return idOrderItem;
	}

	public void setIdOrderItem(Long idOrderItem) {
		this.idOrderItem = idOrderItem;
	}

	public OrderC getOrder() {
		return order;
	}

	public void setOrder(OrderC order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
