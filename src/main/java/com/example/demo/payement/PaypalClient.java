package com.example.demo.payement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
@Component
public class PaypalClient {
	
	public PaypalClient() {
		
	}
	String clientId = "AcA4oxqNyt4ZgP3Y66NHv_4xAf4riP8K2hOeimvGIc4heqBEYcKrJKAf7eYr8he7s455vo_opWM1obEl";
	String clientSecret = "EAjcKaaXvnOoWeGwMJQxy-dRp0-oENArG-jvG6cjmtVwHsnQNcexYjKIRJyVTHR4jPqvBAAhqaIiistI";
	public Map<String, Object> createPayment(Double sum){
	    Map<String, Object> response = new HashMap<String, Object>();
	    Amount amount = new Amount();
	    amount.setCurrency("USD");
	
	   sum = new BigDecimal(sum).setScale(2, RoundingMode.HALF_UP).doubleValue();
	   sum=sum*0.11;
	   System.err.println(sum);
	    amount.setTotal(sum.toString());
	    Transaction transaction = new Transaction();
	    transaction.setAmount(amount);
	    List<Transaction> transactions = new ArrayList<Transaction>();
	    transactions.add(transaction);

	    Payer payer = new Payer();
	    payer.setPaymentMethod("paypal");

	    Payment payment = new Payment();
	    payment.setIntent("sale");
	    payment.setPayer(payer);
	    payment.setTransactions(transactions);

	    RedirectUrls redirectUrls = new RedirectUrls();
	    redirectUrls.setCancelUrl("http://localhost:4200/cancel");
	    redirectUrls.setReturnUrl("http://localhost:4200/");
	    payment.setRedirectUrls(redirectUrls);
	    Payment createdPayment;
	    try {
	        String redirectUrl = "";
	        APIContext context = new APIContext(clientId,clientSecret,"sandbox");
	        System.err.println(context);
	        createdPayment = payment.create(context);
	        System.err.println(context);
	        if(createdPayment!=null){
	            List<Links> links = createdPayment.getLinks();
	            for (Links link:links) {
	                if(link.getRel().equals("approval_url")){
	                    redirectUrl = link.getHref();
	                    break;
	                }
	            }
	            response.put("status", "success");
	            response.put("redirect_url", redirectUrl);
	        }
	    } catch (PayPalRESTException e) {
	        System.err.println("Error happened during payment creation!");
	    }
	    return response;
	}
	public Map<String, Object> completePayment(HttpServletRequest req){
		System.err.println("completePayment");
	    Map<String, Object> response = new HashMap();
	    Payment payment = new Payment();
	    payment.setId(req.getParameter("paymentId"));

	    PaymentExecution paymentExecution = new PaymentExecution();
	    paymentExecution.setPayerId(req.getParameter("PayerID"));
	    try {
	        APIContext context = new APIContext(clientId, clientSecret, "sandbox");
	        Payment createdPayment = payment.execute(context, paymentExecution);
	        if(createdPayment!=null){
	            response.put("status", "success");
	            response.put("payment", createdPayment);
	        }
	    } catch (PayPalRESTException e) {
	        System.err.println(e.getDetails());
	    }
	    return response;
	}

}
