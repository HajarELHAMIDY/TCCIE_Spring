package com.example.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Client implements UserDetails, Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idClient;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String codePostal;
	private String address;
	private int ptsF; 
    private boolean compte; 
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)

	@JoinTable(name = "USERS_ROLES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID") })
	private Set<Role> roles=new HashSet<Role>();

	@ManyToOne
	private Ville ville;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_login", referencedColumnName = "idLogin")
	private Login login;

	public Client() {
		

	}

	public Client(Long idClient, String firstName, String lastName, String email, String phone, String address,
			String codePostal, Ville city, Login login) {

		this.idClient = idClient;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.codePostal = codePostal;
		this.phone = phone;
		this.address = address;
		this.ville = city;
		this.login = login;
		
	}
	

	public boolean isCompte() {
		return compte;
	}

	public void setCompte(boolean compte) {
		this.compte = compte;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	public void setIdClient(Long idClient) {

		this.idClient = idClient;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}
	

	public int getPtsF() {
		return ptsF;
	}

	public void setPtsF(int ptsF) {
		this.ptsF = ptsF;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Ville getCity() {
		return ville;
	}

	public void setCity(Ville city) {
		this.ville = city;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		this.roles.forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;

	}

	@JsonProperty(access = Access.READ_ONLY)
	public List<String> getRolesUser() {

		List<String> authorities = new ArrayList<>();
		this.roles.forEach(role -> {

			authorities.add(role.getName());
		});
		return authorities;

	}

	@Override
	@JsonIgnore
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.login.getPwd();
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
