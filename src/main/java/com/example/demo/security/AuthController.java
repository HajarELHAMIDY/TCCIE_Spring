package com.example.demo.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Client;



@CrossOrigin
@RestController
@RequestMapping(value="/tccie/authentification")
public class AuthController {

		@Autowired
		private TokenUtil jwtTokenUtil;

	    @Autowired
	    private AuthenticationManager authenticationManager;
	    
	    
	    @PostMapping(value = {""})
	    public JwtResponse signIn(@RequestBody SignInRequest signInRequest) {
	    	System.err.println(signInRequest.getPwd());
			final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							signInRequest.getEmail(),
							signInRequest.getPwd()
					)
			);
			System.err.println("test");
			SecurityContextHolder.getContext().setAuthentication(authentication);
			final String token = jwtTokenUtil.generateToken(authentication);
			System.err.println(token);
			return new JwtResponse(token,Constants.ACCESS_TOKEN_VALIDITY_SECONDS,((Client)authentication.getPrincipal()).getIdClient());

	    }
	    
}
