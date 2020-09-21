package com.example.demo.security;


public class SignInRequest {

	private String email;
	private String pwd;
	public SignInRequest(String email, String pwd) {
		this.email = email;
		this.pwd = pwd;
	}
	public SignInRequest() {
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	

}
