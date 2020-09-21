package com.example.demo.payement;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
@CrossOrigin("*")
@RestController
public class PaymentCardController {

    private StripeClient  stripeClient; 
    @Autowired PaymentCardController 
    (StripeClient stripeClient) { 
        this.stripeClient = stripeClient; 
    } 

    @PostMapping ("/payment/charge") 
    public Charge chargeCard (@RequestBody TokenPayement token ,  HttpServletRequest request) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{ 	
    	
    	String tokenPayement = token.getToken(); 
    	int montant = token.getMontant(); 
        System.err.println("payement card test "+montant);
        return this.stripeClient.chargeCreditCard (tokenPayement, 100*100); 
    
}

}
class TokenPayement {
	private  String token; 
	private int montant;
	public String getToken() {
		return token;
	}
	public void setToken(String tokenPayement) {
		this.token = tokenPayement;
	}
	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	} 
	
}
