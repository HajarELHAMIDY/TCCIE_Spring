package com.example.demo.payement;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
@CrossOrigin("*")
@Component
public class StripeClient {

    @Autowired 
    StripeClient () { 
        Stripe. apiKey = "sk_test_51HF67jLAcy1IeV8PRSt8Dl3vV9jkWwGS3KKhz4WRYrTMcVbn6wd4fLuGBqf7FjwK1c0fXzaEjplbvxho8Z4olTL600WzaSbVBd"; 
    } 

     public Charge  chargeCreditCard (String jeton, int montant) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException { 
        Map <String, Object> chargeParams = new HashMap <String, Object> (); 
        chargeParams.put("amount", montant); 
        chargeParams.put ("currency", "mad"); 
        chargeParams.put ("source", jeton); 
     
        Charge charge = Charge.create(chargeParams); 
         return charge; 
    } 

}
