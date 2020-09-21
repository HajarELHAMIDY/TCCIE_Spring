package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class Login implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLogin;
	private String pwd;
	private String email;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_client", referencedColumnName = "idClient")
	private Client client;
	public Login() {
		
	}
	public Login(Long idLogin, String pwd, String username) {
		super();
		this.idLogin = idLogin;
		this.pwd = pwd;
		this.email = username;
	}
	public Long getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(Long idLogin) {
		this.idLogin = idLogin;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setUsername(String username) {
		this.email = username;
	}

	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	

}
