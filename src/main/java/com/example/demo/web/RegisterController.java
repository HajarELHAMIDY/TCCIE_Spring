package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.LoginRepository;
import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.VilleRepository;
import com.example.demo.entities.Client;
import com.example.demo.entities.ClientDetails;
import com.example.demo.entities.Login;
import com.example.demo.entities.Ville;
import com.example.demo.security.AuthController;
import com.example.demo.security.JwtResponse;
import com.example.demo.security.SignInRequest;

@CrossOrigin("*")
@RestController
public class RegisterController {
	@Autowired
	private LoginRepository logindao; 
	@Autowired
	private ClientRepository clientdao;
	
	@Autowired
	private RoleRepository roledao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private VilleRepository villedao; 
	@Autowired
	private AuthController authController; 
	
	@PostMapping("/registerUser")
	public JwtResponse saveUser(@Validated @RequestBody ClientDetails clientDetails)  throws Exception {

		String email = clientDetails.getClient().getEmail(); 
		if(email!=null && !"".equals(email)) {
			Client c = clientdao.findByEmail(email); 
			if(c!=null) {
				throw new Exception("Client avec l'email "+email+" existe déjà !!"); 
			}
			else {
				
			}
		}
		Client client = clientDetails.getClient();
		Login login = clientDetails.getLogin();
		Ville ville = villedao.findByName(clientDetails.getVille());
		
		login.setClient(client);
		client.setLogin(login);
		String password= login.getPwd();
		login.setPwd(encoder.encode(login.getPwd()));
		client.addRole(roledao.findByName("CLIENT"));
		client.setVille(ville);
		client.setCompte(true);
		
		logindao.save(login);
		clientdao.save(client); 
		
		return authController.signIn(new SignInRequest(login.getEmail(),password));
		
	}
	
	public Client fetchUserByEmail(String email) {
		return clientdao.findByEmail(email);
	}

}
