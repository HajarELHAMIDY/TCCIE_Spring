package com.example.demo.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class PayementOnline implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private Date datePayement;
	//n'est pas sécurisé 
	private Long cardNumber;
	private String cardType;
/*	@OneToOne(mappedBy="payement")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Order order;*/
	
	public PayementOnline() {
		
	}

	public PayementOnline(Long id, Date datePayement, Long cardNumber, String cardType, OrderC order) {

		this.id = id;
		this.datePayement = datePayement;
		this.cardNumber = cardNumber;
		this.cardType = cardType;
		//this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatePayement() {
		return datePayement;
	}

	public void setDatePayement(Date datePayement) {
		this.datePayement = datePayement;
	}

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/*
	 * public Order getOrder() { return order; }
	 * 
	 * public void setOrder(Order order) { this.order = order; }
	 */
	
	

}
