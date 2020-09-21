package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

public class OrderForm {
	private Client client = new Client();
	private String ville;
	private String methode;
	private List<OrderItems> products = new ArrayList();

	public String getMethode() {
		return methode;
	}

	public void setMethode(String methode) {
		this.methode = methode;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<OrderItems> getProducts() {
		return products;
	}

	public void setProducts(List<OrderItems> products) {
		this.products = products;
	}

}
