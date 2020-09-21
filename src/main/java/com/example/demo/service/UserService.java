package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ClientRepository;
import com.example.demo.entities.Client;

@Service(value = "userService")
public class UserService implements UserDetailsService{
	@Autowired
	private ClientRepository clientdao; 
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Client user = clientdao.findByEmail(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return user;
		
	}

}
