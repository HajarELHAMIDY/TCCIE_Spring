package com.example.demo.security;

public class JwtResponse {

	private String token;
	private long expiration;
	private long user_id;
	public JwtResponse() {
	
	}
	public JwtResponse(String token, long expiration, long user_id) {
		
		this.token = token;
		this.expiration = expiration;
		this.user_id = user_id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public long getExpiration() {
		return expiration;
	}
	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	
	

    
}
