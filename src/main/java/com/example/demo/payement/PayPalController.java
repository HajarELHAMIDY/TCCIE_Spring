package com.example.demo.payement;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("*")
@RestController
public class PayPalController {
  @Autowired
    private final PaypalClient payPalClient;
    @Autowired
    PayPalController(PaypalClient payPalClient){
        this.payPalClient = payPalClient;
    }

    @PostMapping(value = "/paypal/payment")
    public Map<String, Object> makePayment(@Validated @RequestBody Double sum){
    	
        return payPalClient.createPayment(sum);
    }
    @PostMapping(value = "/complete/payment")
    public Map<String, Object> completePayment(HttpServletRequest request){
        return payPalClient.completePayment(request);
    }
}