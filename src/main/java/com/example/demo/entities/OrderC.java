package com.example.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

public class OrderC implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOrder;
	
	private double totalAmount;
	private String date;
	@ManyToOne
	private Client client;
	private String currency="MAD";
	private String methodePayement; 
	@OneToMany(mappedBy="order")
	private List<OrderItems> orderItems;

	public OrderC() {
	
	}

	public OrderC(Long idOrder, double totalAmount, String date, Client client) {
		super();
		this.idOrder = idOrder;
		this.orderItems = new ArrayList<OrderItems>();
		this.totalAmount = totalAmount;
		this.date = date;
		this.client = client;
	}

	public String getMethodePayement() {
		return methodePayement;
	}

	public void setMethodePayement(String methodePayement) {
		this.methodePayement = methodePayement;
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String curracy) {
		this.currency = curracy;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	

}
