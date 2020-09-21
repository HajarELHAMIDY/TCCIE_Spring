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
public class Admin implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAdmin;
	private String lastName;
	private String firstName;
	private String email;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_login", referencedColumnName = "idLogin")
	private Login login;
	public Admin(Long idAdmin, String lastName, String firstName, String email, Login login) {
		super();
		this.idAdmin = idAdmin;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.login = login;
	}
	public Admin() {
		
	}
	public Long getIdAdmin() {
		return idAdmin;
	}
	public void setIdAdmin(Long idAdmin) {
		this.idAdmin = idAdmin;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	
	

}
